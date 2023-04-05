package com.app.springbackend.model.post;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sn_category", schema = "klv_schema")
public class SnCategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "category_name")
    private String categoryName;
    @Basic
    @Column(name = "category_description")
    private String categoryDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnCategoryEntity that = (SnCategoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(categoryDescription, that.categoryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, categoryDescription);
    }
}
