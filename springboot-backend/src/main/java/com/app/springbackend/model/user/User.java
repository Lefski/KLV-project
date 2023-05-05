package com.app.springbackend.model.user;

import com.app.springbackend.model.bookmark.UserBookmark;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user", schema = "jft_database")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sn_user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Post> posts;
}
