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
    private Long materialCategory;
    private Long furnitureCategory;
    private String imageUrl;
}