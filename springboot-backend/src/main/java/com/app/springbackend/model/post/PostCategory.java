package com.app.springbackend.model.post;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sn_post_category", schema = "klv_database")
public class PostCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Basic
    @Column(name = "category_description", nullable = false, length = -1)
    private String categoryDescription;

    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;
}
