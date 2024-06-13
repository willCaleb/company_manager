package com.will.loja.model.dto;

import com.will.loja.model.entity.Company;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyDTO extends AbstractDTO<Integer, Company>{

    private Integer id;

    private String name;

    private String cnpj;

    private Date inclusionDate;

    private boolean active;

    private Date changeDate;

}
