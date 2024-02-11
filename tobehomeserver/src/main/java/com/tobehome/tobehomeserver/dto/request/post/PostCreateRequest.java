package com.tobehome.tobehomeserver.dto.request.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private Long userId;
    private String title;
    private String shortDescription;
    private String content;
    private String type;
    private Long materialCategoryId;
    private Long furnitureCategoryId;
    private String imageUrl;
}