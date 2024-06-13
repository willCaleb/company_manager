package com.will.loja.utils;

import java.util.*;
import java.util.stream.Stream;

public class ListUtils {

    public static boolean isNullOrEmpty (Collection<?> list) {
        return Utils.isEmpty(list) || list.size() == 0;
    }

    public static boolean isNotNullOrEmpty(Collection<?> list) {
        return !isNullOrEmpty(list);
    }

    public static Collection<?> ofNullable(Collection<?> list) {
        return isNotNullOrEmpty(list) ? list : new ArrayList<>();
    }

    public static <T> List<T> toList(T... args) {
        return Arrays.asList(args);
    }

    public static int size(Collection<?> list) {
        return isNullOrEmpty(list) ? 0 : list.size();
    }

    public static Stream stream(Collection<?> list) {
        return list.stream();
    }

    public static boolean equals(Collection list1, Collection list2) {
        if (list1 == list2) {
            return true;
        } else if (list1 != null && list2 != null && list1.size() == list2.size()) {
            Iterator it1 = list1.iterator();
            Iterator it2 = list2.iterator();
            Object obj1;
            Object obj2;

            while(true) {
                if (it1.hasNext() && it2.hasNext()) {
                    obj1 = it1.next();
                    obj2 = it2.next();
                    if (obj1 == null) {
                        if (obj2 == null) {
                            continue;
                        }
                    } else if (obj1.equals(obj2)) {
                        continue;
                    }

                    return false;
                }

                return !it1.hasNext() && !it2.hasNext();
            }
        } else {
            return false;
        }
    }

}
