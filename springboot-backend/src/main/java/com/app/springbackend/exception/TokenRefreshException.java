package com.app.springbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when an operation regarding token refreshment fails.
 * <p>
 * Responds with a status of {@link HttpStatus#FORBIDDEN}.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {

    /**
     * Constructs a new token refresh exception with the specified token and detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param token the token which triggered the exception.
     * @param message the detailed message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public TokenRefreshException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
