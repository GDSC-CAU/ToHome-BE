package com.tobehome.tobehomeserver.dto.request.member;

import com.tobehome.tobehomeserver.domain.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberSignInRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(encodedPassword)
                .build();
    }
}
