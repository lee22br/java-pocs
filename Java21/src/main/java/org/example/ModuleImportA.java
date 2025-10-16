package org.example;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleImportA {
    public static void main (String[] args) {
        String[] fruits = new String[]{"apple", "berry", "citrus"};
        Map<String, String> m =  Stream.of(fruits)
                .collect(Collectors.toMap(
                        s -> s.toUpperCase().substring(0, 1),
                        Function.identity()));
    }
}
