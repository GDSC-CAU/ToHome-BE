package com.tobehome.tobehomeserver.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class PostRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "interior_post_id", nullable = false)
    private Post interiorPost;

    @ManyToOne
    @JoinColumn(name = "product_post_id", nullable = false)
    private Post productPost;

    @Builder
    public PostRelation(Post interiorPost, Post productPost) {
        this.interiorPost = interiorPost;
        this.productPost = productPost;
    }
}

