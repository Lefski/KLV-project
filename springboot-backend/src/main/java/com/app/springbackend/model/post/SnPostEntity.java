package com.app.springbackend.model.post;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "sn_post", schema = "klv_schema")
public class SnPostEntity {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(Long postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }

    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public byte[] getPostImageUrl() {
        return postImageUrl;
    }

    public void setPostImageUrl(byte[] postImageUrl) {
        this.postImageUrl = postImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnPostEntity that = (SnPostEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(postAuthor, that.postAuthor) &&
                Objects.equals(postStatus, that.postStatus) &&
                Objects.equals(datePosted, that.datePosted) &&
                Objects.equals(dateModified, that.dateModified) &&
                Objects.equals(postTitle, that.postTitle) &&
                Objects.equals(postDescription, that.postDescription) &&
                Objects.equals(postContent, that.postContent) &&
                Objects.equals(postType, that.postType) &&
                Arrays.equals(postImageUrl, that.postImageUrl);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id,
                postAuthor,
                postStatus,
                datePosted,
                dateModified,
                postTitle,
                postDescription,
                postContent,
                postType
        );
        result = 31 * result + Arrays.hashCode(postImageUrl);
        return result;
    }
}
