package org.example.method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit Tests for Factory Method
 */
class FactoryMethodTest {

    @Test
    @DisplayName("Should create an SmsMessage when using SmsNotifier")
    void shouldCreateSmsMessage() {
        Notifier notifier = new SmsNotifier();

        Message message = notifier.createMessage();

        assertNotNull(message, "The factory should not return null");
        assertInstanceOf(SmsMessage.class, message, "Message should be an instance of SmsMessage");
    }

    @Test
    @DisplayName("Should create an EmailMessage when using EmailNotifier")
    void shouldCreateEmailMessage() {
        Notifier notifier = new EmailNotifier();

        Message message = notifier.createMessage();

        assertNotNull(message, "The factory should not return null");
        assertInstanceOf(EmailMessage.class, message, "Message should be an instance of EmailMessage");
    }
}