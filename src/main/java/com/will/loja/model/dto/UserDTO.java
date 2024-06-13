package com.will.loja.model.dto;

import com.will.loja.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends AbstractDTO<Integer, User>{

    private Integer id;

    private String username;

    private String password;

    private Date inclusionDate;

    private Date changeDate;



}
