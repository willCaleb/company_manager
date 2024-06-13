package com.will.loja.utils;

import java.math.BigDecimal;

public class NumericUtils {

    public static boolean isGreater(Number a, Number b) {
        return a.doubleValue() > b.doubleValue();
    }

    public static boolean isGreaterOrEquals(Number a, Number b) {
        return isGreater(a, b) || isEquals(a, b);
    }

    public static boolean isEquals(Number a, Number b) {
        return a.doubleValue() == b.doubleValue();
    }

    public static boolean isSmaller(Number a, Number b) {
        return a.doubleValue() < b.doubleValue();
    }

    public static BigDecimal notNullable(BigDecimal valor) {
        return valor != null ? valor : BigDecimal.ZERO;
    }
}