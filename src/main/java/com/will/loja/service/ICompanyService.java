package com.will.loja.service;

import com.will.loja.model.dto.CompanyDTO;
import com.will.loja.model.entity.Company;
import com.will.loja.repository.CompanyRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface ICompanyService extends IAbstractService<Company, CompanyDTO, CompanyRepository> {

    Company include(HttpServletRequest httpRequest, Company company);

    void edit(Integer companyId, Company company);
}
