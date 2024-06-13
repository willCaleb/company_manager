package com.will.loja.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "lja_tenant", schema = "public")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "schema")
    private String schema;

    @Column(name = "inclusion_date")
    private Date inclusionDate;

    @Column(name = "validation_date")
    private Date validationDate;

    @Column(name = "active")
    private boolean active;

}
