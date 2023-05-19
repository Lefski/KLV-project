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

/**
 * Handles operations related to the refresh token.
 */
@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    @Value("${klv.app.refresh-token-expiration-ms}")
    private Long REFRESH_TOKEN_EXPIRATION_TIME_IN_MILLIS;

    private final UserRefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    /**
     * Retrieves a {@link UserRefreshToken} by the given token string.
     *
     * @param token The token string to search for.
     * @return An {@link Optional} containing the {@link UserRefreshToken} if found.
     */
    public Optional<UserRefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    /**
     * Creates a new refresh token for a user with the given user ID.
     *
     * @param userId The ID of the user for whom to create the refresh token.
     * @return The created {@link UserRefreshToken}.
     */
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

    /**
     * Verifies that the given refresh token has not expired.
     *
     * @param token The {@link UserRefreshToken} to verify.
     * @return The verified {@link UserRefreshToken}, if it is not expired.
     * @throws TokenRefreshException if the given token is expired.
     */
    public UserRefreshToken verifyRefreshTokenExpiration(UserRefreshToken token) {
        if (token.getDateExpiration().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(
                    token.getToken(), "Refresh token was expired. Make a new signing request");
        }
        return token;
    }

    /**
     * Deletes all refresh tokens associated with the user with the given user ID.
     *
     * @param userId The ID of the user for whom to delete all refresh tokens.
     */
    @Transactional
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUser(userRepository
                .findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Error: User not found")));
    }
}
