package com.tobehome.tobehomeserver.dto.request.post;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private Long user_id;
    private String title;
    private String shortDescription;
    private String content;
    private String type;
    private Long materialCategory;
    private Long furnitureCategory;
    private String imageUrl;
}