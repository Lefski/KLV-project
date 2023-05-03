package com.app.springbackend.model.user;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user_passport", schema = "klv_database")
public class UserPassport {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Basic
    @Column(name = "phone_number", nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
