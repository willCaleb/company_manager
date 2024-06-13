package com.will.loja.pattern;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    @Value("${api.base-url}")
    private String getBaseUrl;

    public static String BASE_URL;

    @PostConstruct
    public void init() {
        BASE_URL = getBaseUrl;
    }

    public static final String ASC = "asc";

    public static final String DESC = "desc";

    public static final String SORT = "sort";

    public static final String OLD_VALUE = "oldValue";

    public static final String NEW_VALUE = "newValue";

    public static final String INCLUSION_DATE = "inclusionDate";

    public static final String CHANGE_DATE = "changeDate";
}
