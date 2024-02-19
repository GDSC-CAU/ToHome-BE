package com.tobehome.tobehomeserver.domain.entity;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name = "short_description", nullable = false, length = 100)
    private String shortDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name ="type")
    private String type;

    // @JoinColumn(name = "material_category")
    @Column(name = "material_category")
    private Long materialCategory;

    //    @ManyToOne
//    @JoinColumn(name = "furniture_category")
    @Column(name = "furniture_category")
    private Long furnitureCategory;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "image_url2", length = 500)
    private String imageUrl2;

    @Column(name = "image_url3", length = 500)
    private String imageUrl3;

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Rel {
        private Long p;
        private String x;
        private String y;

        // 생성자, getter 및 setter 메서드 등을 추가할 수 있음
    }
    @ElementCollection
    private List<Rel> rel;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Builder
    public Post(Long userId, String title, String shortDescription, String content, String type,
                Long materialCategory, Long furnitureCategory, String imageUrl, String imageUrl2, String imageUrl3,
                List<Rel> rel,Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.type = type;
        this.materialCategory = materialCategory;
        this.furnitureCategory = furnitureCategory;
        this.imageUrl = imageUrl;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
        this.rel = rel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
