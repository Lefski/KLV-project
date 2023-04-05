package com.app.springbackend.model.user;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "sn_user", schema = "klv_schema")
public class SnUserEntity {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Object getUserRole() {
        return userRole;
    }

    public void setUserRole(Object userRole) {
        this.userRole = userRole;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public byte[] getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(byte[] userUrl) {
        this.userUrl = userUrl;
    }

    public byte[] getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(byte[] userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnUserEntity that = (SnUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userRole, that.userRole) &&
                Objects.equals(lastLogin, that.lastLogin) &&
                Objects.equals(dateJoined, that.dateJoined) &&
                Arrays.equals(userUrl, that.userUrl) &&
                Arrays.equals(userImageUrl, that.userImageUrl);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, password, userEmail, userRole, lastLogin, dateJoined);
        result = 31 * result + Arrays.hashCode(userUrl);
        result = 31 * result + Arrays.hashCode(userImageUrl);
        return result;
    }
}
