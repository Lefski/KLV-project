package com.app.springbackend.model.user;

import com.app.springbackend.model.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user", schema = "klv_database")
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

    @Basic
    @Column(name = "last_login", nullable = false)
    private Timestamp lastLogin;

    @Basic
    @Column(name = "date_joined", nullable = false)
    private Timestamp dateJoined;

    @Basic
    @Column(name = "user_image_url")
    private byte[] userImageUrl;

    @OneToOne
    @MapsId
    private UserPassport userPassport;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sn_user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Post> posts;
}
