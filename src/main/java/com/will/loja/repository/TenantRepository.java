package com.will.loja.repository;

import com.will.loja.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TenantRepository extends JpaRepository<Tenant, Integer>, JpaSpecificationExecutor<Tenant> {
}
