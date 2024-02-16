package com.tobehome.tobehomeserver.dto.request.member;

import com.tobehome.tobehomeserver.domain.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

//@Getter @Setter
//@NoArgsConstructor
//public class MemberLogInRequest {
//    private String nickname;
//    private String password;
//}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberLogInRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .nickname(this.nickname)
                .password(encodedPassword)
                .build();
    }

}