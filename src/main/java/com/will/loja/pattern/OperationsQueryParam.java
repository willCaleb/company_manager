package com.will.loja.pattern;

import java.util.Arrays;
import java.util.List;

public interface OperationsQueryParam {

    String OFFSET = "offset";

    String LIMIT = "limit";

    String SIZE = "size";

    String PAGE = "page";

    String MATCHER = "matcher";

    String SORT = "sort";

    List<String> OPERATIONS = Arrays.asList(
            OperationsQueryParam.OFFSET,
            OperationsQueryParam.LIMIT,
            OperationsQueryParam.MATCHER,
            OperationsQueryParam.SORT,
            OperationsQueryParam.SIZE,
            OperationsQueryParam.PAGE);

}