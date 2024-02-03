package com.tobehome.tobehomeserver.dto.request.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequestDto {
    private String email;
    private String nickname;
    private String password;
}
