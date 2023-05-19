package com.app.springbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for testing role-based access control.
 * Defines endpoints that can be accessed depending on user's role.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Endpoint that can be accessed by users with the role USER, MODERATOR, or ADMIN.
     *
     * @return {@link org.springframework.http.ResponseEntity} with "User content" message.
     */
    @GetMapping
    public ResponseEntity<String> publicAccess() {
        return ResponseEntity.ok("Public content");
    }

    /**
     * Endpoint that can be accessed by users with the role USER, MODERATOR, or ADMIN.
     *
     * @return {@link org.springframework.http.ResponseEntity} with "User content" message.
     */
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("User content");
    }

    /**
     * Endpoint that can be accessed by users with the role MODERATOR.
     *
     * @return {@link org.springframework.http.ResponseEntity} with "Moderator content" message.
     */
    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> moderatorAccess() {
        return ResponseEntity.ok("Moderator content");
    }

    /**
     * Endpoint that can be accessed by users with the role ADMIN.
     *
     * @return {@link org.springframework.http.ResponseEntity} with "Admin content" message.
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Admin content");
    }
}
