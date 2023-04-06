package com.app.springbackend.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user", schema = "klv_database")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "user_email")
    private String userEmail;

    @Basic
    @Column(name = "user_role")
    private Object userRole;

    @Basic
    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Basic
    @Column(name = "date_joined")
    private Timestamp dateJoined;

    @Basic
    @Column(name = "user_url")
    private byte[] userUrl;

    @Basic
    @Column(name = "user_image_url")
    private byte[] userImageUrl;

    @OneToOne(mappedBy = "sn_user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserPassportEntity userPassport;
}
