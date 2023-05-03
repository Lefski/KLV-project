package com.app.springbackend.repo;

import com.app.springbackend.model.user.UserPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPassportRepository extends JpaRepository<UserPassport, Long> {

    Optional<UserPassport> findByUserId(Long userId);
}
