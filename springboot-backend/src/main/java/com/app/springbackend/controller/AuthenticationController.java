package com.app.springbackend.controller;

import com.app.springbackend.exception.TokenRefreshException;
import com.app.springbackend.model.user.UserRefreshToken;
import com.app.springbackend.payload.request.AuthenticationRequest;
import com.app.springbackend.payload.request.RegisterRequest;
import com.app.springbackend.payload.request.TokenRefreshRequest;
import com.app.springbackend.payload.response.MessageResponse;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.security.jwt.JwtUtils;
import com.app.springbackend.security.services.AuthenticationService;
import com.app.springbackend.security.services.TokenRefreshService;
import com.app.springbackend.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Controller responsible for all authentication-related operations.
 * Handles requests to create, verify, and refresh authentication tokens for users,
 * as well as to register, login and logout users.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final TokenRefreshService tokenRefreshService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * Handle the user registration request.
     *
     * @param request The {@link com.app.springbackend.payload.request.RegisterRequest} payload containing details of the user to be registered.
     * @return {@link org.springframework.http.ResponseEntity} containing message of the registration result.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
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

    /**
     * Handle the user authentication request.
     *
     * @param request The {@link com.app.springbackend.payload.request.AuthenticationRequest} payload containing the username and password of the user to be authenticated.
     * @return {@link org.springframework.http.ResponseEntity} containing details of the authentication result including JWT tokens.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        tokenRefreshService.deleteByUserId(userDetails.getId());

        String refreshToken = tokenRefreshService.createRefreshToken(userDetails.getId()).getToken();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtUtils.generateTokenCookie(userDetails).toString())
                .header(HttpHeaders.SET_COOKIE, jwtUtils.generateRefreshTokenCookie(refreshToken).toString())
                .body(authenticationService.authenticate(userDetails));
    }

    /**
     * Handle the user logout request.
     *
     * @return {@link org.springframework.http.ResponseEntity} with a message indicating the user has been successfully logged out.
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!Objects.equals(principle.toString(), "anonymousUser")) {
            tokenRefreshService.deleteByUserId(
                    ((UserDetailsImpl) principle).getId()
            );
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jwtUtils.getCleanTokenCookie().toString())
                .header(HttpHeaders.SET_COOKIE, jwtUtils.getCleanRefreshTokenCookie().toString())
                .body(MessageResponse
                        .builder()
                        .message("You've been signed out")
                        .build()
                );
    }

    /**
     * Handle the refresh token request.
     *
     * @param request The {@link com.app.springbackend.payload.request.TokenRefreshRequest} payload containing the current refresh token.
     * @return {@link org.springframework.http.ResponseEntity} containing details of the refresh operation including a new JWT token or an error message.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            @RequestBody TokenRefreshRequest request
    ) {
        String refreshToken = request.getRefreshToken();

        if (refreshToken != null && refreshToken.length() > 0) {
            return tokenRefreshService
                    .findByToken(refreshToken)
                    .map(tokenRefreshService::verifyRefreshTokenExpiration)
                    .map(UserRefreshToken::getUser)
                    .map(user -> ResponseEntity
                            .ok()
                            .header(HttpHeaders.SET_COOKIE, jwtUtils.generateTokenCookie(user).toString())
                            .body(MessageResponse
                                    .builder()
                                    .message("Token is refreshed successfully")
                                    .build()
                            )
                    )
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database"));
        }

        return ResponseEntity
                .badRequest()
                .body(MessageResponse
                        .builder()
                        .message("Refresh token is empty")
                        .build()
                );
    }
}
