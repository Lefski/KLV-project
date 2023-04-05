package com.app.springbackend.model.post;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sn_post_categories", schema = "klv_schema")
public class SnPostCategoriesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "post_id")
    private Long postId;
    @Basic
    @Column(name = "category_id")
    private Long categoryId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnPostCategoriesEntity that = (SnPostCategoriesEntity) o;
        return Objects.equals(postId, that.postId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, categoryId);
    }
}
