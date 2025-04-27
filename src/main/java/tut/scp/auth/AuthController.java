package tut.scp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tut.scp.dto.AuthRequest;
import tut.scp.service.IAuth;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuth authService;

    @Autowired
    public AuthController(IAuth authService) {
        this.authService = authService;
    }

    @PostMapping("/complete-registration")
    public ResponseEntity<?> completeRegistration(@RequestBody AuthRequest authRequest) {
        return authService.setPassword(authRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        return authService.signIn(authRequest);
    }

}
