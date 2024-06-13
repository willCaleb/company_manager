package com.will.loja.service.impl;

import com.will.loja.config.context.TenantContext;
import com.will.loja.model.dto.CompanyDTO;
import com.will.loja.model.entity.Company;
import com.will.loja.repository.CompanyRepository;
import com.will.loja.service.ICompanyService;
import com.will.loja.service.IDatabaseService;
import com.will.loja.service.TenantService;
import com.will.loja.utils.DateUtils;
import com.will.loja.validator.CompanyValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service
@RequiredArgsConstructor
public class CompanyService extends AbstractService<Company, CompanyDTO, CompanyRepository> implements ICompanyService {

    private final CompanyRepository repository;
    private final TenantService tenantService;

    private final IDatabaseService databaseService;


    CompanyValidator validator = new CompanyValidator();

    @Override
    public Company include(HttpServletRequest httpRequest, Company company) {
        validator.validateRequiredFields(company);
        String origin = httpRequest.getHeader("Origin");
        tenantService.createTenant(origin);

        databaseService.createTable(Company.class, origin);
        TenantContext.setCurrentTenant(origin);

        return repository.save(company);
    }

    @Override
    public void edit(Integer companyId, Company company) {

    }
}
