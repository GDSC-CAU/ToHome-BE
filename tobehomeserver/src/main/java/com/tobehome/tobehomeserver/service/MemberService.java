package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.member.MemberRequestDto;
import com.tobehome.tobehomeserver.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Lazy
public class MemberService implements UserDetailsService {
    private final MemberJpaRepository memberJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

//    public Member signup(MemberRequestDto memberRequestDto) {
//        String encodedPassword = passwordEncoder.encode(memberRequestDto.getPassword());
//        Member member = new Member(memberRequestDto.getEmail(), memberRequestDto.getNickname(), memberRequestDto.getPassword());
//        return memberJpaRepository.save(member);
//    }

    public Long save(MemberRequestDto dto) {
        return memberJpaRepository.save(Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword()))    // 패스워드는 암호화하여 저장
                .build()).getId();
    }
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        return memberJpaRepository.findByNickname(nickname);
    }

}
