package com.will.loja.controller;

import com.will.loja.exception.CustomException;
import com.will.loja.exception.ErrorResponse;
import com.will.loja.model.dto.AbstractDTO;
import com.will.loja.model.entity.AbstractEntity;
import com.will.loja.pattern.Constants;
import com.will.loja.pattern.OperationsParam;
import com.will.loja.pattern.OperationsPath;
import com.will.loja.pattern.OperationsQueryParam;
import com.will.loja.service.impl.AbstractService;
import com.will.loja.utils.ListUtils;
import com.will.loja.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.criteria.Predicate;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RestController
@SuppressWarnings("unchecked")
public abstract class AbstractController<E extends AbstractEntity<?, DTO>, DTO extends AbstractDTO<?, E>> extends AbstractService<E, DTO, JpaRepository> {

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomException(CustomException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    public List<E> toEntityList(List<DTO> list) {
        List retorno = new ArrayList();
        if (Utils.isEmpty(list)) return retorno;

        for (DTO item : list) {
            retorno.add(((AbstractDTO) item).toEntity());
        }
        return retorno;
    }

    public <D extends AbstractDTO, T extends AbstractEntity> List<T> toEntityList(List<D> dtoList, Class<T> clazz) {
        List<T> entityList = new ArrayList<>();

        for (D dto : dtoList) {
            entityList.add(new ModelMapper().map(dto, clazz));
        }
        return entityList;
    }

    @GetMapping(OperationsPath.ID)
    @ResponseBody
    public DTO findById(@PathVariable("id") Integer id) {

        E retorno = (E) getRepository(getEntityClass()).findById(id).orElseGet(() -> {
            throw new CustomException("Não foi encontrado " + getEntityClass().getSimpleName() + " com o id " + id);
        });
        return retorno.toDto();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<DTO> findAll() {
        return toDtoList(findAllEntity());
    }

    private Sort sortById(String ordem) {
        if (Constants.ASC.equalsIgnoreCase(ordem)) {
            return Sort.by(Sort.Direction.ASC, OperationsParam.ID);
        }
        return Sort.by(Sort.Direction.DESC, OperationsParam.ID);
    }

    @PutMapping(OperationsPath.ID + "/edit")
    public void editar(@RequestBody DTO abstractDto, @PathVariable(OperationsParam.ID) Integer id) {
        super.editar(abstractDto.toEntity(), id);
    }

    public Class<E> getEntityClass() {
        Type[] genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        return (Class) genericTypes[0];
    }

    public List<DTO> toDtoList(List<E> entityList) {
        List retorno = new ArrayList();
        if (Utils.isEmpty(entityList)) return retorno;
        for (E item : entityList) {
            retorno.add(((AbstractEntity) item).toDto());
        }
        return retorno;
    }

    public <I extends AbstractEntity, T extends AbstractDTO> List<T> toDtoList(List<I> entityList, Class<?> clazz) {
        List<T> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add((T) entity.toDto()));
        return dtoList;
    }

    private List<E> findAllEntity() {
        return getRepository(getEntityClass()).findAll(sortById(null));
    }

    private Specification<E> getSpecificationEqualOrLike(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String chave = entry.getKey();
                Object valor = entry.getValue();

                if (valor != null) {
                    if (String.class.equals(root.get(chave).getJavaType())) {
                        predicates.add(criteriaBuilder.like(root.get(chave), "%" + valor + "%"));
                    } else if (root.get(chave).getJavaType().isEnum()) {
                        Class<Enum> enumType = (Class<Enum>) root.get(chave).getJavaType();
                        Enum enumValue = Enum.valueOf(enumType, valor.toString());
                        predicates.add(criteriaBuilder.equal(root.get(chave), enumValue));
                    } else {
                        predicates.add(criteriaBuilder.equal(root.get(chave), valor));
                    }
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private void resolverSort(Map<String, Object> filters) {
        Sort sort;
        if (filters.containsKey(Constants.SORT)) {
            sort = sortById(filters.get(Constants.SORT).toString());
        } else {
            sort = sortById(null);
        }
        filters.put(Constants.SORT, sort);
    }

    @GetMapping("/filter")
    public Page<DTO> getFiltered(@RequestParam(required = false) Map<String, Object> filters, Pageable pageable) {
        if (onlyPageableFilters(filters)) {
            Sort sort = sortById(Utils.nvl(filters.get(Constants.SORT).toString(), null));

            Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
            return fromPagedEntityToPagedDTO(getAllPaged(pageableWithSort), pageableWithSort);
        }
        resolverSort(filters);
        Page<E> allPagedAndFiltered = getAllPagedAndFiltered(filters, pageable);
        return fromPagedEntityToPagedDTO(allPagedAndFiltered, pageable);
    }

    private Page<E> getAllPaged(Pageable pageable) {
        return (Page<E>) getRepository(getEntityClass()).findAll(pageable);
    }

    private boolean onlyPageableFilters(Map<String, Object> filters) {
        final List<String> filterOperations = new ArrayList<>();

        ListUtils.stream(OperationsQueryParam.OPERATIONS)
                .filter(filters::containsKey)
                .forEach(operation -> filterOperations.add(operation.toString()));

        return ListUtils.isNotNullOrEmpty(filterOperations)
                && Utils.equals(filters.size(), filterOperations.size());
    }

    private Page<E> getAllPagedAndFiltered(@RequestParam(required = false) Map<String, Object> filters, Pageable pageable) {

        List<String> operations = OperationsQueryParam.OPERATIONS;

        operations.forEach(operation -> {
            if (filters.containsKey(operation)) {
                filters.remove(operation);
            }
        });

        return getSpecificationRepository(getEntityClass())
                .findAll(getSpecificationEqualOrLike(filters), pageable);
    }

    private Page<DTO> fromPagedEntityToPagedDTO(Page<E> page, Pageable pageable) {
        List<E> content = page.getContent();
        List<DTO> dtoList = toDtoList(content);
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

}