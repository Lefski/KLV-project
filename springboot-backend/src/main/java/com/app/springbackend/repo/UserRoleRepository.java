package com.app.springbackend.repo;

import com.app.springbackend.model.user.EUserRole;
import com.app.springbackend.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByRoleName(EUserRole roleName);
}
