package com.app.springbackend.security.services;

import com.app.springbackend.model.user.*;
import com.app.springbackend.payload.request.AuthenticationRequest;
import com.app.springbackend.payload.request.RegisterRequest;
import com.app.springbackend.payload.response.MessageResponse;
import com.app.springbackend.payload.response.TokenResponse;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.repo.UserRoleRepository;
import com.app.springbackend.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenRefreshService tokenRefreshService;

    public MessageResponse register(
            RegisterRequest request
    ) {

        Set<String> stringRoles = request.getRole();
        Set<UserRole> roles = new HashSet<>();

        if (stringRoles.size() == 0) {
            UserRole roleUser = userRoleRepository.findByRoleName(EUserRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
            roles.add(roleUser);
        } else {
            stringRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        UserRole roleAdmin = userRoleRepository.findByRoleName(EUserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(roleAdmin);
                    }
                    case "moderator" -> {
                        UserRole roleModerator = userRoleRepository.findByRoleName(EUserRole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(roleModerator);
                    }
                    default -> {
                        UserRole roleUser = userRoleRepository.findByRoleName(EUserRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
                        roles.add(roleUser);
                    }
                }
            });
        }

        User user = User
                .builder()
                .username(request.getUsername())
                .userEmail(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .bookmarks(new HashSet<>())
                .build();

        userRepository.save(user);

        return MessageResponse
                .builder()
                .message("User registered successfully")
                .build();
    }

    public TokenResponse authenticate(AuthenticationRequest request) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        UserRefreshToken refreshToken = tokenRefreshService.createRefreshToken(userDetails.getId());

        return TokenResponse
                .builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .userEmail(userDetails.getUserEmail())
                .roles(roles)
                .token(jwtUtils.generateToken(authentication))
                .refreshToken(refreshToken.getToken())
                .type("Bearer")
                .build();
    }
}
