package com.app.springbackend.model.user;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sn_user_passport", schema = "klv_schema")
public class SnUserPassportEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "birth_date")
    private Date birthDate;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private SnUserEntity snUserByUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnUserPassportEntity that = (SnUserPassportEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(country, that.country) &&
                Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, phoneNumber, birthDate, country, zipCode);
    }

    public SnUserEntity getSnUserByUserId() {
        return snUserByUserId;
    }

    public void setSnUserByUserId(SnUserEntity snUserByUserId) {
        this.snUserByUserId = snUserByUserId;
    }
}
