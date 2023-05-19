package com.app.springbackend.security.jwt;

import com.app.springbackend.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter class extends {@link org.springframework.web.filter.OncePerRequestFilter} to guarantee a single execution per request dispatch.
 * <p>
 * It is used for JWT token authentication filtering.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * This method checks if the request has a valid JWT token. If it has a valid JWT token,
     * it sets the authentication in the context to specify that the current user is authenticated.
     *
     * @param request the {@link jakarta.servlet.http.HttpServletRequest} which may include a JWT token in the header.
     * @param response the {@link jakarta.servlet.http.HttpServletResponse} to the request.
     * @param filterChain a {@link jakarta.servlet.FilterChain} that we can call to pass the request/response to the next filter in the chain.
     *
     * @throws ServletException in case of general servlet exception
     * @throws IOException in case of an I/O error
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {

            final String token = jwtUtils.getTokenFromCookies(request);

            if (token != null
                    && jwtUtils.isTokenValid(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtUtils.extractUsername(token));

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception ex) {
            logger.error("Cannot set user authentication: {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
