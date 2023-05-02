package com.app.springbackend.model.post;

import com.app.springbackend.model.user.User;
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
@Table(name = "sn_post", schema = "klv_database")
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "post_status", nullable = false)
    private String postStatus;

    @Basic
    @Column(name = "date_posted", nullable = false)
    private Timestamp datePosted;

    @Basic
    @Column(name = "date_modified", nullable = false)
    private Timestamp dateModified;

    @Basic
    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Basic
    @Column(name = "post_description", nullable = false, length = -1)
    private String postDescription;

    @Basic
    @Column(name = "post_content", nullable = false, length = -1)
    private String postContent;

    @Basic
    @Column(name = "post_type", nullable = false)
    private String postType;

    @Basic
    @Column(name = "post_image_url", nullable = false)
    private byte[] postImageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sn_post_categories",
            joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<PostCategory> categories;

    @ManyToOne
    @JoinColumn(name = "post_author_id", nullable = false)
    private User author;
}
