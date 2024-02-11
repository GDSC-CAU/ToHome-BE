package com.tobehome.tobehomeserver.dto.request.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {
    private String title;
    private String shortDescription;
    private String content;
    private Long materialCategoryId;
    private Long furnitureCategoryId;
    private String imageUrl;
}