package com.will.loja.model.dto;

import com.will.loja.model.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleDTO extends AbstractDTO<Integer, Role>{

    private Integer id;

    private String name;

}
