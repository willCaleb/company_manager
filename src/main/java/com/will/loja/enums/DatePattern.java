package com.will.loja.enums;

public enum DatePattern {

    ddMMyyyy("dd/MM/yyyy"),
    yyyyMMdd("yyyy/MM/dd")
    ;

    private final String value;

    DatePattern(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}