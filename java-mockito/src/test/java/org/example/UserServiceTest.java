package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class) // Initializes mocks automatically
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService; // Injects userRepository and emailService here

    @Captor
    private ArgumentCaptor<String> emailCaptor;

    @Test
    @DisplayName("Should update username and send notification successfully")
    void updateUsernameTest() {
        // 1. GIVEN (Stubbing)
        User fakeUser = new User(1L, "Old Name", "test@example.com");
        given(userRepository.findById(1L)).willReturn(Optional.of(fakeUser));

        // 2. WHEN (Action)
        userService.updateUsername(1L, "New Name");

        // 3. THEN (Verification & Assertions)

        // Verify interaction with DB
        verify(userRepository, times(1)).save(any(User.class));

        // Verify void method and capture argument
        verify(emailService).sendEmail(emailCaptor.capture(), contains("updated"));

        assertEquals("test@example.com", emailCaptor.getValue());
        assertEquals("New Name", fakeUser.getName());
    }


    @Test
    @DisplayName("Testing void method behavior specifically")
    void testVoidMethodError() {
        // Stubbing a void method to throw an exception
        doThrow(new RuntimeException("Mail Server Down"))
                .when(emailService).sendEmail(anyString(), anyString());

        User fakeUser = new User(1L, "Name", "email@test.com");
        given(userRepository.findById(1L)).willReturn(Optional.of(fakeUser));

        assertThrows(RuntimeException.class, () -> {
            userService.updateUsername(1L, "New Name");
        });
    }
}