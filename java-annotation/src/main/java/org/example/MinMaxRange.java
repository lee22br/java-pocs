package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // This annotation is for fields (variables)
public @interface MinMaxRange {
    int min() default 0;
    int max() default 100;
    String message() default "Value out of range!";
}
