package tut.scp.shared.service;

import org.springframework.http.ResponseEntity;
import tut.scp.dto.AuthRequest;

public interface IAuth {
    ResponseEntity<?> setPassword(AuthRequest request);
    ResponseEntity<?> signIn(AuthRequest authRequest);
}
