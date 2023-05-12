package com.app.springbackend.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user_role", schema = "jft_database")
public class UserRole {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private EUserRole roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
