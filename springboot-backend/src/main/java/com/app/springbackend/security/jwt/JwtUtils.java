package com.app.springbackend.security.jwt;

import com.app.springbackend.model.user.User;
import com.app.springbackend.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/**
 * Provides JWT token utilities.
 */
@Service
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${klv.app.sign-key}")
    private String SIGN_KEY;

    @Value("${klv.app.token-expiration-ms}")
    private Integer TOKEN_EXPIRATION_TIME_IN_MILLIS;

    @Value("${klv.app.jwt-cookie-name}")
    private String JWT_COOKIE;

    @Value("${klv.app.jwt-refresh-cookie-name}")
    private String JWT_REFRESH_COOKIE;

    /**
     * Generates a JWT token stored in a cookie for a given {@link UserDetailsImpl} object.
     *
     * @param userDetails {@link UserDetailsImpl} to generate a token for.
     * @return {@link ResponseCookie} containing the JWT token.
     */
    public ResponseCookie generateTokenCookie(UserDetailsImpl userDetails) {
        return generateCookie(JWT_COOKIE, generateToken(userDetails), "/api");
    }

    public ResponseCookie generateTokenCookie(User user) {
        return generateTokenCookie(UserDetailsImpl.build(user));
    }

    public ResponseCookie generateRefreshTokenCookie(String refreshToken) {
        return generateCookie(JWT_REFRESH_COOKIE, refreshToken, "/api/auth/refresh-token");
    }

    /**
     * Retrieves a JWT token from cookies in the provided {@link HttpServletRequest}.
     *
     * @param request The {@link HttpServletRequest} which may include a JWT token in the cookies.
     * @return JWT token if found in the cookies, otherwise null.
     */
    public String getTokenFromCookies(HttpServletRequest request) {
        return getValueFromCookies(request, JWT_COOKIE);
    }

    public String getRefreshTokenFromCookies(HttpServletRequest request) {
        return getValueFromCookies(request, JWT_REFRESH_COOKIE);
    }

    public ResponseCookie getCleanTokenCookie() {
        return ResponseCookie
                .from(JWT_COOKIE, "")
                .path("/api")
                .build();
    }

    public ResponseCookie getCleanRefreshTokenCookie() {
        return ResponseCookie
                .from(JWT_REFRESH_COOKIE, "")
                .path("/api/auth/refresh-token")
                .build();
    }

    private ResponseCookie generateCookie(String name, String value, String path) {
        return ResponseCookie
                .from(name, value)
                .path(path)
                .maxAge(24 * 60 * 60)
                .httpOnly(true)
                .build();
    }

    private String getValueFromCookies(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     Extracts the username from the JWT token.
     @param token A String representing the JWT token.
     @return A String representing the username extracted from the JWT token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     Extracts a claim from the JWT token using a provided claims resolver function.
     @param token A String representing the JWT token.
     @param claimsResolver A Function that extracts a specific claim from a set of Claims.
     @param <T> The type of the claim being extracted.
     @return The claim extracted from the JWT token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     Extracts all claims from the JWT token using the sign in key.
     @param token A String representing the JWT token.
     @return A Claims object containing all the claims extracted from the JWT token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     Returns the signing key used to generate JSON Web Tokens (JWTs).
     The signing key is generated from the base64-encoded string constant SIGN_KEY.
     The key is created using HMAC-SHA algorithms.
     @return A Key object representing the signing key used to generate JWTs.
     @throws IllegalArgumentException if the SIGN_KEY constant is not properly base64-encoded.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SIGN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetailsImpl userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     Generates a JSON Web Token (JWT) with the given extra claims and user details, signed with a secure key.
     @param extraClaims additional claims to include in the JWT payload.
     @param userDetails user details to be included in the JWT payload as the "sub" (subject) claim.
     @return a signed JWT with the given extra claims and user details.
     */
    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetailsImpl userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME_IN_MILLIS))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     Checks whether a given JWT token is valid for a specific user.
     @param token the JWT token to validate
     @return true if the token is valid for the user, false otherwise.
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
