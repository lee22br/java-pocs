package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class FunctionUtil {

    public static <T, U, R> List<R> listCombiner(
            List<T> list1, List<U> list2, BiFunction<T, U, R> combiner) {
        List<R> result = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(combiner.apply(list1.get(i), list2.get(i)));
        }
        return result;
    }
}
