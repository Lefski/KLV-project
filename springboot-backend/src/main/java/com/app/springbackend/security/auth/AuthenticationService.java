package com.app.springbackend.security.auth;

import com.app.springbackend.model.user.UserEntity;
import com.app.springbackend.model.user.EUserRole;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        UserEntity user = UserEntity
                .builder()
                .username(request.getUsername())
                .userEmail(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(EUserRole.USER)
                .lastLogin(new Timestamp(System.currentTimeMillis()))
                .dateJoined(new Timestamp(System.currentTimeMillis()))
                .userUrl(new byte[0])
                .userImageUrl(new byte[0])
                .build();

        userRepository.save(user);
        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow(); // TODO: handle exception
        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
