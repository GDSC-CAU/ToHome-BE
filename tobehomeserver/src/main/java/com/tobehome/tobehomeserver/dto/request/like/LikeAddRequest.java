package com.tobehome.tobehomeserver.dto.request.like;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeAddRequest {
    private Long user_id;
    private Long postId;
}