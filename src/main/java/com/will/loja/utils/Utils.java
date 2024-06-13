package com.will.loja.utils;

import org.springframework.util.ObjectUtils;

import java.util.function.BiFunction;

public class Utils {

    public static boolean isEmpty(Object object) {

        return object == null || ObjectUtils.isEmpty(object);
    }

    public static boolean isNotEmpty(Object object) {

        return !isEmpty(object);
    }

    public static boolean equals(Object obj, Object compareTo) {

        return obj.equals(compareTo);
    }

    public static <T> T nvl(Object change, T changeTo) {
        if (isNotEmpty(change)) {
            return (T) change;
        }
        return changeTo;
    }

    @FunctionalInterface
    public interface BinaryFunctio<T> extends BiFunction<Object, Object, T> {
        default boolean test(Object obj1, Object obj2) {
            return isNotEmpty(obj1) && isNotEmpty(obj2);
        }
    }
}
