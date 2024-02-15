package com.tobehome.tobehomeserver.dto.response.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLogInResponse {
    private String accessToken;

    public MemberLogInResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
