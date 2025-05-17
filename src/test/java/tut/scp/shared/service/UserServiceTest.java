package tut.scp.shared.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tut.scp.dto.UserRequest;
import tut.scp.entity.User;
import tut.scp.enums.Role;
import tut.scp.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    private UserRequest request;

    @BeforeEach
    void setUp() {
        request = new UserRequest();
        request.setEmail("test@tut4life.ac.za");
        request.setFirstName("Test");
        request.setLastName("User");
        request.setRole(Role.ROLE_STUDENT);
    }

    @Test
    void testAddUser_UserAlreadyExists() {
        User existingUser = new User();
        existingUser.setEmail(request.getEmail());

        when(userRepo.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(existingUser));

        // Act
        ResponseEntity<?> response = userService.addUser(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("User already exists", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void testAddUser_UserCreatedSuccessfully() {
        // Arrange
        when(userRepo.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = userService.addUser(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User successfully created", ((Map<?, ?>) response.getBody()).get("message"));
        verify(userRepo).save(any(User.class));
    }
}

