package com.app.springbackend.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user", schema = "klv_database")
public class UserEntity implements UserDetails {

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
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

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

    /**
     * Method to get list of all authorities
     *
     * @return list of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
