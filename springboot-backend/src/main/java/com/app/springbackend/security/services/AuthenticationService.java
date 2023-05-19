package com.app.springbackend.security.services;

import com.app.springbackend.model.user.EUserRole;
import com.app.springbackend.model.user.User;
import com.app.springbackend.model.user.UserRole;
import com.app.springbackend.payload.request.RegisterRequest;
import com.app.springbackend.payload.response.MessageResponse;
import com.app.springbackend.payload.response.UserInfoResponse;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.repo.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles authentication related operations.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user with the given request data. The request includes
     * username, email, password, and roles. If no role is provided, the user
     * will be assigned a default role.
     *
     * @param request The {@link RegisterRequest} object that includes the
     *                information necessary to register a new user.
     * @return {@link MessageResponse} indicating successful registration.
     */
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

    /**
     * Generates user information based on the provided {@link UserDetailsImpl} object.
     *
     * @param userDetails The user details from which to generate the information.
     * @return {@link UserInfoResponse} containing user information including id,
     *         username, email, and roles.
     */
    public UserInfoResponse authenticate(UserDetailsImpl userDetails) {

        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return UserInfoResponse
                .builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .userEmail(userDetails.getUserEmail())
                .roles(roles)
                .build();
    }
}
