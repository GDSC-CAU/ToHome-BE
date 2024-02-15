package com.tobehome.tobehomeserver.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }


    /*
        UserDetails 인터페이스의 메서드 구현
        - UserDetails: 스프링 시큐리티에서 사용자의 정보를 담는 인터페이스
     */
    @Override   // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("member"));
    }

    @Override   // 사용자의 id 반환(고유한 값)
    public String getUsername() {
        return nickname;
    }

    @Override   // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return true;    // true: 만료되지 않음
    }

    @Override   // 계정 잠김 여부 반환
    public boolean isAccountNonLocked() {
        return true;    // true: 잠기지 않음
    }

    @Override   // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;   //
    }

    @Override   // 계정 활성화 여부 반환
    public boolean isEnabled() {
        return true;    // true: 활성화
    }
}
