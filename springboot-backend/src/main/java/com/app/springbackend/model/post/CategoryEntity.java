package com.app.springbackend.model.post;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "sn_category", schema = "klv_database")
public class CategoryEntity {

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

    @ManyToMany(mappedBy = "categories")
    Set<PostEntity> posts;

//    @OneToMany(mappedBy = "category")
//    Set<PostCategoriesEntity> posts;
}
