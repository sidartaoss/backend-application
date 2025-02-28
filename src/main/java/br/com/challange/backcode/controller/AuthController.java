package br.com.challange.backcode.controller;

import br.com.challange.backcode.api.AuthApi;
import br.com.challange.backcode.core.jwt.JwtTokenProvider;
import br.com.challange.backcode.models.req.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController implements AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            final AuthenticationManager authenticationManager,
            final JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ResponseEntity<?> login(final LoginRequest loginRequest) {
        final var username = loginRequest.username();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        loginRequest.password()));
        String token = jwtTokenProvider.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

