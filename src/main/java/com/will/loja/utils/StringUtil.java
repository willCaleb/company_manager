package com.will.loja.utils;

import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;


public class StringUtil {


    public static boolean isNullOrEmpty(String field) {
        return field == null || field.equals("") || field.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String field) {
        return !isNullOrEmpty(field);
    }

    public static String notNullable(String field) {
        return Utils.isNotEmpty(field) ? field : "";
    }

    public static String formatMessage(String msg, Object... arguments) {
        return MessageFormat.format(msg, arguments);
    }

    public static String removeSpaces(String field) {
        return field.replaceAll(" ", "");
    }

    public static String normalize(String field) {
        field = field.replaceAll("^\\p{L}\\p{N}", "").replaceAll("-", "");
        return Normalizer.normalize(field, Normalizer.Form.NFD);
    }

    public static String toString(Object obj) {
        return obj.toString();
    }

    public static String capitalize(String field) {
        return field.substring(0, 1).toUpperCase().concat(field.substring(1));
    }

    public static String capitalizeAllPhrase(String phrase) {
        String[] words = phrase.split(" ");
        String capitalizedPhrase = "";
        for (int i = 0; i < words.length; i++) {
            words[i] = capitalize(words[i]);
            capitalizedPhrase = capitalizedPhrase.concat(words[i]).concat(" ");
        }
        return capitalizedPhrase;
    }


    public static Function<String, Integer> trimmedLength = str -> str.trim().length();

    public static Function<String, String> removeSpaces = str -> str.replaceAll(" ", "");

    public static Predicate<String> isNotEmpty = str -> str.replaceAll(" ", "").trim().length() > 0;

    public static Consumer<String> print = System.out::println;

    public static ToIntBiFunction<String, String> somarTamanhos = (str1, str2) -> str1.length() + str2.length();
}

