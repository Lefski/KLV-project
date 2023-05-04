package com.app.springbackend.repo;

import com.app.springbackend.model.user.User;
import com.app.springbackend.model.user.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    Optional<UserRefreshToken> findByToken(String token);

    @Modifying
    Long deleteByUser(User user);
}
