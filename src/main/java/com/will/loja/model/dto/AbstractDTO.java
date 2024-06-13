package com.will.loja.model.dto;


import lombok.Data;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.will.loja.model.IIdentificador;
import com.will.loja.model.entity.AbstractEntity;

@Data
@SuppressWarnings("unchecked")
public abstract class AbstractDTO<I extends Number, E extends AbstractEntity> implements IIdentificador, Serializable {

    public E toEntity() {
        Class<E> entityClass = getEntityClass();

        ModelMapper modelMapper = new ModelMapper();

        return  modelMapper.map(this, entityClass);
    }

    private Class<E> getEntityClass() {
        Type[] genericTypes = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();

        return (Class) genericTypes[1];
    }

    public List<E> toEntity(List<E> dtoList) {
        return dtoList.stream().map(this::apply).collect(Collectors.toList());
    }

    private E apply(E dto) {
        return new ModelMapper().map(dto, getEntityClass());
    }
}
