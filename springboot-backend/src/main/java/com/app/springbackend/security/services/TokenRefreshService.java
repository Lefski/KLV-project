package com.app.springbackend.security.services;

import com.app.springbackend.model.user.UserRefreshToken;
import com.app.springbackend.repo.UserRefreshTokenRepository;
import com.app.springbackend.repo.UserRepository;
import com.app.springbackend.exception.TokenRefreshException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    @Value("${klv.app.refresh-token-expiration-ms}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME_IN_MILLIS;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public Optional<UserRefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public UserRefreshToken createRefreshToken(Long userId) {
        return refreshTokenRepository.save(
            UserRefreshToken
                .builder()
                .user(userRepository
                        .findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Error: User not found")))
                .token(UUID.randomUUID().toString())
                .dateExpiration(new Timestamp(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME_IN_MILLIS))
                .build()
        );
    }

    public UserRefreshToken verifyRefreshTokenExpiration(UserRefreshToken token) {
        if (token.getDateExpiration().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(
                    token.getToken(), "Refresh token was expired. Make a new signing request");
        }
        return token;
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found")));
    }
}
