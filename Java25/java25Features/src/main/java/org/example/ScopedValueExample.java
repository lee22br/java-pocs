package org.example;

import static java.lang.ScopedValue.where;

public class ScopedValueExample {

    private static final ScopedValue<String> USER = ScopedValue.newInstance();
    private static final ScopedValue<Integer> SESSION = ScopedValue.newInstance();

    static void main() {
        where(USER, "Alice")
                .where(SESSION, 12345)
                .run(() -> {
                    processRequest();
                    nestedCall();
                });
    }

    static void processRequest() {
        System.out.println("User: " + USER.get() + " Session: " + SESSION.get());
    }

    static void nestedCall() {
        where(USER, "Bob").run(() -> System.out.println("Nested: " + USER.get()));
        System.out.println("Back to: " + USER.get());
    }
}