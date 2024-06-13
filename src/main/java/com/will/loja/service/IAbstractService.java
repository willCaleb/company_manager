package com.will.loja.service;

import com.will.loja.model.dto.AbstractDTO;
import com.will.loja.model.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbstractService<E extends AbstractEntity<?, D>, D extends AbstractDTO<?, E>, R extends JpaRepository> {

    E findAndValidate(Integer id);
}
