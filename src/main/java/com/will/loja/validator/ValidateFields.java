package com.will.loja.validator;

import com.will.loja.exception.CustomException;
import com.will.loja.utils.ListUtils;
import com.will.loja.utils.NumericUtils;
import com.will.loja.utils.StringUtil;
import com.will.loja.utils.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValidateFields {

    private List< String> mandatoryFields = new ArrayList<>();

    public void add(Object obj, String name) {
        if (Utils.isEmpty(obj)) {
            mandatoryFields.add(name);
            return;
        }
        if (obj instanceof Collection) {
            if (ListUtils.isNullOrEmpty((Collection<?>) obj)) {
                mandatoryFields.add(name);
            }
        } else if (obj instanceof String) {
            if (StringUtil.isNullOrEmpty((String)obj)) {
                mandatoryFields.add(name);
            }
        } else if (Utils.isEmpty(obj) && obj.getClass().isArray()) {
            mandatoryFields.add(name);
        }
    }

    public void addValorZeroable(Number valor, String name) {
        if (!NumericUtils.isGreaterOrEquals(valor, 0)) {
            mandatoryFields.add(name);
        }
    }
    public void addValorNotZeroabme(Number valor, String name) {
        if (!NumericUtils.isGreater(valor, 0)) {
            mandatoryFields.add(name);
        }
    }

    public void validate() {
        if (ListUtils.isNotNullOrEmpty(mandatoryFields)) {
            throw new CustomException("Os seguintes campos são obrigatórios : [ " , mandatoryFields);
        }
    }

}