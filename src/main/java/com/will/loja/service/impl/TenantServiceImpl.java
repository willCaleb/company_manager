package com.will.loja.service.impl;

import com.will.loja.model.entity.Tenant;
import com.will.loja.repository.TenantRepository;
import com.will.loja.service.IDatabaseService;
import com.will.loja.service.TenantService;
import com.will.loja.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void createTenant(String schemaName) {

        if (doesSchemaExist(schemaName)) {
            return;
        }
        jdbcTemplate.execute("CREATE SCHEMA " + schemaName);
        Tenant tenant = new Tenant();

        tenant.setActive(true);
        tenant.setSchema(schemaName);
        tenant.setInclusionDate(DateUtils.getDate());
        tenantRepository.save(tenant);
    }

    public boolean doesSchemaExist(String schemaName) {
        String query = "SELECT COUNT(*) FROM information_schema.schemata WHERE schema_name = ?";

        Integer count = jdbcTemplate.queryForObject(query, new Object[]{schemaName}, Integer.class);
        return count != null && count > 0;
    }
}
