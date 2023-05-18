package com.app.springbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для тестирования доступа пользователей.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * Публичный доступ.
     *
     * @return Ответ с публичным контентом и статусом OK.
     */
    @GetMapping
    public ResponseEntity<String> publicAccess() {
        return ResponseEntity.ok("Public content");
    }

    /**
     * Доступ для пользователей.
     *
     * @return Ответ с контентом для пользователей и статусом OK.
     */
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("User content");
    }

    /**
     * Доступ для модераторов.
     *
     * @return Ответ с контентом для модераторов и статусом OK.
     */
    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> moderatorAccess() {
        return ResponseEntity.ok("Moderator content");
    }

    /**
     * Доступ для администраторов.
     *
     * @return Ответ с контентом для администраторов и статусом OK.
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Admin content");
    }
}
