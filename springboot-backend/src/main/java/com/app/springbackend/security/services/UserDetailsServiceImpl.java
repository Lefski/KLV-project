package com.app.springbackend.security.services;

import com.app.springbackend.model.user.User;
import com.app.springbackend.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the {@link UserDetailsService} interface.
 * <p>
 * Used for loading user-specific data during security authentication.
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * Locates the user based on the username. Used by the security framework.
     *
     * @param username the username identifying the user whose data is required.
     * @throws UsernameNotFoundException if the user could not be found or the user has no granted authorities.
     * @return a fully populated {@link UserDetails} object (never null).
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
