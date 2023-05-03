package com.app.springbackend.controller;


import com.app.springbackend.model.user.UserRefreshToken;
import com.app.springbackend.payload.request.AuthenticationRequest;
import com.app.springbackend.payload.request.TokenRefreshRequest;
import com.app.springbackend.payload.response.MessageResponse;
import com.app.springbackend.payload.response.TokenRefreshResponse;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.exception.TokenRefreshException;
import com.app.springbackend.security.jwt.JwtUtils;
import com.app.springbackend.security.services.AuthenticationService;
import com.app.springbackend.payload.request.RegisterRequest;
import com.app.springbackend.security.services.TokenRefreshService;
import com.app.springbackend.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final TokenRefreshService tokenRefreshService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }

        if (userRepository.existsByUserEmail(request.getUserEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken (
            @RequestBody TokenRefreshRequest request
    ) {
        String requestRefreshToken = request.getRefreshToken();

        return tokenRefreshService
                .findByToken(requestRefreshToken)
                .map(tokenRefreshService::verifyRefreshTokenExpiration)
                .map(UserRefreshToken::getUser)
                .map(user -> ResponseEntity.ok(
                        new TokenRefreshResponse(
                                jwtUtils.generateToken(UserDetailsImpl.build(user)),
                                requestRefreshToken,
                                "Bearer"
                        )
                ))
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database"));
    }
}
