package com.app.springbackend.config;

import com.app.springbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Autowired
    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     Returns a {@link UserDetailsService} implementation that retrieves a {@link com.app.springbackend.model.user.UserEntity User} entity from the database
     using the provided {@code username} parameter.
     @return an instance of {@link UserDetailsService}.
     @throws UsernameNotFoundException if a user with the given username is not found in the database.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     Returns an instance of {@link AuthenticationProvider} that uses
     the {@link UserDetailsService} and {@link PasswordEncoder}
     to authenticate users.
     @return the {@link AuthenticationProvider} instance.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     Returns an instance of {@link AuthenticationManager} using the provided {@link AuthenticationConfiguration}.
     @param config the {@link AuthenticationConfiguration} object to use
     @return an instance of {@link AuthenticationManager}
     @throws Exception if an error occurs while building the {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     Returns an instance of {@link PasswordEncoder} that uses BCrypt algorithm.
     @return the {@link PasswordEncoder} instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
