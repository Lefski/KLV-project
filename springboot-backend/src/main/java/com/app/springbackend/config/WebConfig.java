package com.app.springbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures Cross-Origin Resource Sharing (CORS) for the web application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings for the entire application, allowing all origin patterns and HTTP methods.
     * The maximum age for preflight requests is set to 3600 seconds (1 hour).
     * This configuration also allows all headers and credentials.
     *
     * @param registry the {@link CorsRegistry} to add mappings to.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(3600)
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
