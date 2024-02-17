package com.tobehome.tobehomeserver.dto.request.post;

import com.tobehome.tobehomeserver.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String shortDescription;
    private String imageUrl;

    public static PostDTO fromEntity(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setShortDescription(post.getShortDescription());
        postDTO.setImageUrl(post.getImageUrl());
        return postDTO;
    }
}

