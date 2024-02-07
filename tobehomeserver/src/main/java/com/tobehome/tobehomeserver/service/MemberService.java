package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.member.MemberLogInRequest;
import com.tobehome.tobehomeserver.dto.request.member.MemberSignInRequest;
import com.tobehome.tobehomeserver.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberJpaRepository memberJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 닉네임 중복 체크(중복되면 true)
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return memberJpaRepository.existsByNickname(nickname);
    }

    /**
     * 회원가입
     */
    public Long signup(MemberSignInRequest dto) {
        Member member = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        System.out.println(member.getNickname());
        return memberJpaRepository.save(member).getId();
    }

    /**
     * 로그인
     */
    public Member login(MemberSignInRequest dto) {
        Optional<Member> optionalMember = memberJpaRepository.findByNickname(dto.getNickname());

        // nickname이 일치하는 Member가 없으면 null 반환
        if (optionalMember.isEmpty()) {
            return null;
        }

        Member member = optionalMember.get();

        // password가 일치하지 않으면 null 반환
        if (!member.getPassword().equals(dto.getPassword())) {
            return null;
        }

        return member;
    }

    /**
     * ID로 회원 조회
     */
    public Member getMember(Long id) {
        return memberJpaRepository.findById(id).orElse(null);
    }


    /**
     * 닉네임으로 회원 조회
     */
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        return member;
    }

}
