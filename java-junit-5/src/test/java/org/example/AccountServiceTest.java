package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bank Account Service")
class AccountServiceTest {

    private final AccountService service = new AccountService();

    @Nested
    @DisplayName("Withdrawal")
    class WithdrawalTests {

        @Test
        @DisplayName("decrease balance when funds are sufficient")
        void withdrawSuccess() {
            Account acc = new Account(1000.0);
            service.withdraw(acc, 400.0);
            assertEquals(600.0, acc.getBalance(), "Balance should be 600 after withdrawal");
        }

        @Test
        @DisplayName("throw exception when balance is insufficient")
        void withdrawInsufficientFunds() {
            Account acc = new Account(100.0);

            InsufficientFundsException exception = assertThrows(
                    InsufficientFundsException.class,
                    () -> service.withdraw(acc, 500.0)
            );
            assertTrue(exception.getMessage().contains("Shortfall of 400.0"));
        }
    }

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