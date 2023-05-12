package com.app.springbackend.model.bookmark;

import com.app.springbackend.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_user_bookmark", schema = "jft_database")
public class UserBookmark {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "article_url", nullable = false)
    private byte[] articleUrl;

    @Basic
    @Column(name = "article_title", nullable = false)
    private String articleTitle;

    @Basic
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
