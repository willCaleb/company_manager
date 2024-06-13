package com.will.loja.model.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.will.loja.model.dto.AbstractDTO;
import com.will.loja.model.IIdentificador;
import com.will.loja.utils.ClassUtils;
import com.will.loja.utils.ListUtils;
import com.will.loja.utils.Utils;
import com.will.loja.exception.CustomException;
import com.will.loja.annotations.DtoFieldIgnore;
import com.will.loja.annotations.OnlyField;

@SuppressWarnings("unchecked")
public abstract class AbstractEntity<I extends Number, DTO extends AbstractDTO> implements IIdentificador, Serializable {

    public DTO toDto() {
        Type[] genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<DTO> dtoClass = (Class) genericTypes[1];
        return filter(this, dtoClass, null);
    }

    public DTO toDto(AbstractEntity entity, Class<DTO> clazz) {
        return filter(entity, clazz, null);
    }

    private DTO toDto(List<String> onlyFields) {
        Type[] genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<DTO> dtoClass = (Class) genericTypes[1];
        return filter(this, dtoClass, onlyFields);
    }
//    TODO implementar toDto para listas
//    public List<DTO> toDtoList() {
//
//    }

    private DTO filter(AbstractEntity entity, Class<DTO> dtoType, List<String> onlyFields) {
        try {
            List<Field> allFieldsFromDto = (List<Field>) ListUtils.toList(dtoType.getDeclaredFields());

            DTO dtoReturn = dtoType.newInstance();

            allFieldsFromDto.forEach(field -> {
                if (!field.isAnnotationPresent(DtoFieldIgnore.class)) {
                    if (Utils.isEmpty(onlyFields) || onlyFields.contains(field.getName())) {
                        try {
                            Method getterMethod = ClassUtils.getGetterMethod(field.getName(), entity.getClass());
                            if (Utils.isEmpty(getterMethod)) {
                                throw new CustomException("O campo não reconhecido: " + field.getName() + entity.getClass().getSimpleName());
                            }
                            Method setterMethod = ClassUtils.getSetterMethod(field.getName(), dtoReturn.getClass());
                            Object invoke = getterMethod.invoke(entity);

                            if (invoke == null) return;

                            final List<String> fieldsToFilter = getOnlyFields(field);

                            if (AbstractDTO.class.isAssignableFrom(field.getType())) {
                                AbstractEntity invokeEntity = (AbstractEntity) invoke;
                                setterMethod.invoke(dtoReturn, invokeEntity.toDto(fieldsToFilter));

                            } else if (field.getType().isAssignableFrom(List.class)) {
                                ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                                Class<?> aClass = (Class<?>) genericType.getActualTypeArguments()[0];
                                if (AbstractDTO.class.isAssignableFrom(aClass)) {
                                    List<AbstractEntity> invokeList = (List<AbstractEntity>) invoke;
                                    if (ListUtils.isNotNullOrEmpty(invokeList)) {
                                        List<DTO> dtos = invokeList
                                                .stream()
                                                .map(inv -> (DTO) inv.toDto(fieldsToFilter))
                                                .collect(Collectors.toList());

                                        setterMethod.invoke(dtoReturn, dtos);
                                    }
                                }
                            } else {
                                setterMethod.invoke(dtoReturn, invoke);
                            }
                        } catch (Exception e ) {
                            System.out.println(e.getMessage() + " " + entity.getClass().getSimpleName() + " " + field.getName());
                        }
                    }
                }
            });
            return dtoReturn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getOnlyFields(Field field) {
        List<String> fieldsToFilter = new ArrayList<>();
        if (field.isAnnotationPresent(OnlyField.class)) {
            OnlyField onlyField = field.getAnnotation(OnlyField.class);
            fieldsToFilter = ListUtils.toList(onlyField.fields());
        }
        return fieldsToFilter;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + getId() + ")";
    }

}
