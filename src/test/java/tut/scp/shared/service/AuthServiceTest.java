package tut.scp.shared.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import tut.scp.dto.AuthRequest;
import tut.scp.entity.User;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private AuthRequest authRequest;

    @BeforeEach
    void setUp() {
        authRequest = new AuthRequest();
        authRequest.setEmail("student@test.com");
        authRequest.setPassword("password123");
    }

    @Test
    void shouldReturnUserNotFound_whenEmailDoesNotExist() {
        when(userService.getUserByEmail(anyString())).thenReturn(Optional.empty());

        ResponseEntity<?> response = authService.setPassword(authRequest);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("User not found", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void shouldReturnAlreadyEnabled_whenUserAlreadyEnabled() {
        User user = new User();
        user.setEnabled(true);

        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<?> response = authService.setPassword(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("User already enabled", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void shouldUpdatePasswordSuccessfully_whenUserIsNotEnabled() {
        User user = new User();
        user.setEnabled(false);
        user.setEmail(authRequest.getEmail());

        when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");

        ResponseEntity<?> response = authService.setPassword(authRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        assertEquals("Password updated successfully", ((Map<?, ?>) response.getBody()).get("message"));
        verify(userService).updateUserPassword(user, "hashedPassword");
    }
}

