package com.tobehome.tobehomeserver.dto.request.comment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {
    private int userId;
    private int postId;
    private String content;
}