package com.will.loja.controller;

import com.will.loja.model.dto.CompanyDTO;
import com.will.loja.model.entity.Company;
import com.will.loja.service.ICompanyService;
import com.will.loja.service.impl.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController extends AbstractController<Company,CompanyDTO> {

    private final ICompanyService companyService;

    @PostMapping
    public CompanyDTO create(HttpServletRequest httpRequest, @RequestBody CompanyDTO companyDTO) {
        return companyService.include(httpRequest, companyDTO.toEntity()).toDto();
    }

}
