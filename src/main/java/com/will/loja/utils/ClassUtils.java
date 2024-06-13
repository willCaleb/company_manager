package com.will.loja.utils;


import com.will.loja.exception.CustomException;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassUtils {

    public static Method getGetterMethod(String fieldName, Class<?> javaBeanClass) {
        return Stream.of(javaBeanClass.getDeclaredMethods())
                .filter(method -> isGetterMethod(method, fieldName))
                .findFirst()
                .orElse(null);
    }

    private static boolean isGetterMethod(Method method, String name) {
        return method.getParameterCount() == 0
                && !Modifier.isStatic(method.getModifiers())
                && (method.getName().equalsIgnoreCase("get" + name) || method.getName().equalsIgnoreCase("is" + name));
    }

    private static boolean isGetterMethod(Method method) {
        return method.getParameterCount() == 0
                && !Modifier.isStatic(method.getModifiers())
                && (method.getName().startsWith("get") || method.getName().startsWith("is"));
    }

    private static boolean isSetterMethod(Method method) {
        return method.getName().startsWith("set");
    }

    public static Method getSetterMethod(String fieldName, Class<?> clazz) {
        return Stream.of(clazz.getDeclaredMethods())
                .filter(method -> isSetterMethod(method, fieldName))
                .findFirst()
                .orElse(null);
    }

    public static boolean isSetterMethod(Method method, String name) {
        return method.getName().startsWith("set") && method.getName().equalsIgnoreCase("set".concat(name));
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {

            Constructor<T> constructor = org.springframework.util.ClassUtils.getConstructorIfAvailable(clazz);

            if (Utils.isNotEmpty(constructor)) {
                return constructor.newInstance();
            } else {
                throw new CustomException("Não foi possível criar nova instância de " + clazz.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException("Não foi possível criar nova instância de ", clazz.getName());
    }

    public static List<Field> getFieldsAsList(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static List<Method> getMethodsAsList(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredMethods());
    }

    public static List<Method> getGetterMethodsAsList(Class<?> clazz) {
        return getMethodsAsList(clazz)
                .stream()
                .filter(ClassUtils::isGetterMethod)
                .collect(Collectors.toList());
    }

    public static List<Method> getSetterMethodsAsList(Class<?> clazz) {
        return getMethodsAsList(clazz)
                .stream()
                .filter(ClassUtils::isSetterMethod)
                .collect(Collectors.toList());
    }


    public static <T> boolean isSameClass(T objectA, Object objectB) {
        Class<?> aClass = objectA.getClass();

        return objectB.getClass().isAssignableFrom(aClass);
    }


}
