package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @ParameterizedTest
    @DisplayName("Testing values source")
    @ValueSource(strings = {"lee@gmail.com", "teste@outlook.com.br", "contato@rio.org.br"})
    void palindromes(String candidate) {
        assertTrue(User.isValidEmail(candidate));
    }

    @Test
    @DisplayName("Testing Grouping")
    void testVoidMethodError() {
        User user = new User(1L, "Leandro", "lee@globo.com");
        assertAll("user",
                () -> assertEquals("Leandro", user.getName()),
                () -> assertEquals("lee@globo.com", user.getEmail())
        );
    }
}
