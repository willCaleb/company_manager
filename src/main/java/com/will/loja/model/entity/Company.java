package com.will.loja.model.entity;

import com.will.loja.model.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TenantId;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "lja_company")
public class Company extends AbstractEntity<Integer, CompanyDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "inclusion_date")
    private Date inclusionDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "change_date")
    private Date changeDate;
}
