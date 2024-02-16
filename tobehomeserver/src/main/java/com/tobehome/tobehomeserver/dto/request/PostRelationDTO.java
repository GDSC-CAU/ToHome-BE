package com.tobehome.tobehomeserver.dto.request;

import com.tobehome.tobehomeserver.domain.entity.PostRelation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRelationDTO {
    private Long id;
    private Long interiorPostId;
    private Long productPostId;

    public static PostRelationDTO from(PostRelation postRelation) {
        PostRelationDTO dto = new PostRelationDTO();
        dto.setId(postRelation.getId());
        dto.setInteriorPostId(postRelation.getInteriorPost().getId());
        dto.setProductPostId(postRelation.getProductPost().getId());
        return dto;
    }
}

