package com.will.loja.validator;

import com.will.loja.model.entity.AbstractEntity;

public interface IValidator<E extends AbstractEntity> {

    void validateRequiredFields(E entity);

    default void validateFieldSize(E entity){}

    default void validateInsert(E entity) {}

    default void validateInsertOuUpdate(E entity){}
}