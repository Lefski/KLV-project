package com.app.springbackend.advice;

import com.app.springbackend.exception.TokenRefreshException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

/**
 * Handles the exception that occurs when there's a problem with the refresh token.
 */
@RestControllerAdvice
public class TokenControllerAdvice {

    /**
     * Handles {@link TokenRefreshException}. If such an exception occurs, this method returns
     * a {@link ErrorMessage} with information about the error, including the HTTP status code,
     * the timestamp when the exception occurred, the error message from the exception, and the description
     * of the request during which the exception occurred.
     *
     * @param e the {@link TokenRefreshException} that occurred.
     * @param request the {@link WebRequest} during which the exception occurred.
     * @return an {@link ErrorMessage} with details about the exception.
     */
    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(TokenRefreshException e, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Timestamp(System.currentTimeMillis()),
                e.getMessage(),
                request.getDescription(false)
        );
    }
}
