package com.will.loja.validator;

import com.will.loja.model.entity.Company;

public class CompanyValidator implements IValidator<Company> {
    @Override
    public void validateRequiredFields(Company entity) {
        ValidateFields validateFields = new ValidateFields();
        validateFields.add(entity.getName(), "Nome");
        validateFields.add(entity.getCnpj(), "CNPJ");
        validateFields.validate();
    }
}
