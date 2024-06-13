package com.will.loja.model.entity;

import com.will.loja.model.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lja_role")
public class Role extends AbstractEntity<Integer, RoleDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
