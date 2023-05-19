package com.app.springbackend.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link org.springframework.security.web.AuthenticationEntryPoint}.
 * <p>
 * Used to commence an authentication scheme at the point of request processing when an authentication is required but isn't present.
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    /**
     * Commences an authentication scheme. This method is automatically called when a client tries to access a secured REST resource
     * without supplying any credentials. We should just send a 401 Unauthorized response because there is no 'login page' to redirect to.
     * Also adds the exception message and some other details to the response.
     *
     * @param request that resulted in an AuthenticationException
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     *
     * @throws IOException in case of an I/O error
     * @throws ServletException in case of general servlet exception
     */
    @Override
    public void commence(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AuthenticationException authException
    ) throws IOException, ServletException {

        logger.error("Unauthorized error: {}", authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>() {
            {
                put("error", "Unauthorized");
                put("message", authException.getMessage());
                put("path", request.getServletPath());
                put("timestamp", new SimpleDateFormat("MM-dd-yyyy HH:mm:ss z").format(new Date()));
                put("statusCode", HttpServletResponse.SC_UNAUTHORIZED);
            }
        };

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
