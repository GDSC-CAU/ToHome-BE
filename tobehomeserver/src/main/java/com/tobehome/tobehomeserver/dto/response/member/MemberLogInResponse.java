package com.tobehome.tobehomeserver.dto.response.member;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MemberLogInResponse {
    private String accessToken;

}
