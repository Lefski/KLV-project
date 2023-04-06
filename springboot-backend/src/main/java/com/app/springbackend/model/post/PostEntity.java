package com.app.springbackend.model.post;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "sn_post", schema = "klv_database")
public class PostEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "post_author")
    private Long postAuthor;

    @Basic
    @Column(name = "post_status")
    private String postStatus;

    @Basic
    @Column(name = "date_posted")
    private Timestamp datePosted;

    @Basic
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Basic
    @Column(name = "post_title")
    private String postTitle;

    @Basic
    @Column(name = "post_description")
    private String postDescription;

    @Basic
    @Column(name = "post_content")
    private String postContent;

    @Basic
    @Column(name = "post_type")
    private String postType;

    @Basic
    @Column(name = "post_image_url")
    private byte[] postImageUrl;

    @ManyToMany
    @JoinTable(name = "sn_post_categories",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<CategoryEntity> categories;

//    @OneToMany(mappedBy = "post")
//    Set<PostCategoriesEntity> categories;
}
