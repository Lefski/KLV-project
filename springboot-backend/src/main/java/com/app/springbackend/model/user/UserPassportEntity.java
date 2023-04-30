package com.app.springbackend.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user_passport", schema = "klv_database")
public class UserPassportEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

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
}
