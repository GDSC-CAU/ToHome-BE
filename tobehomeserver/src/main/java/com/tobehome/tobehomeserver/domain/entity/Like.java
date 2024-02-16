package com.tobehome.tobehomeserver.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_likes")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "postId", nullable = false)
    private Long postId;

    @Builder
    public Like(Long user_id, Long postId) {
        this.userId = user_id;
        this.postId = postId;
    }

    // Getter and Setter methods
    // userId
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // postId
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}