package org.example;

import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            // Check if annotation exist
            if (field.isAnnotationPresent(MinMaxRange.class)) {

                field.setAccessible(true);

                MinMaxRange range = field.getAnnotation(MinMaxRange.class);
                int value = (int) field.get(obj); // Get the value from obj

                if (value < range.min() || value > range.max()) {
                    System.err.println("Validation Error: " + range.message());
                    System.err.println("Current value: " + value);
                } else {
                    System.out.println("Field '" + field.getName() + "' is valid!");
                }
            }
        }
    }
}