package com.tobehome.tobehomeserver.dto.request;

import com.tobehome.tobehomeserver.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String nickname;

    public static MemberDTO from(Member member) {
        return new MemberDTO(member.getId(), member.getEmail(), member.getNickname());
    }
}