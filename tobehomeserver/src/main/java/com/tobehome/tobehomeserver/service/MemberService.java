package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.config.jwt.JwtTokenProvider;
import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.member.MemberLogInRequest;
import com.tobehome.tobehomeserver.dto.request.member.MemberSignInRequest;
import com.tobehome.tobehomeserver.dto.response.member.MemberLogInResponse;
import com.tobehome.tobehomeserver.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
        return memberJpaRepository.save(member).getId();
    }

    /**
     * 로그인
     */
    public MemberLogInResponse login(MemberSignInRequest dto) {
        Optional<Member> optionalMember = memberJpaRepository.findByNickname(dto.getNickname());
        System.out.println("optionalMember: " + optionalMember);

        // nickname이 일치하는 Member가 없는 경우
        if (optionalMember.isEmpty()) {
            System.out.println("optionalMember.isEmpty()");
            return null;
        }

        Member member = optionalMember.get();
        System.out.println("password: " + member.getPassword());

        // password가 일치하지 않으면 null 반환
        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            System.out.println("!passwordEncoder.matches(dto.getPassword(), member.getPassword())");
            return null;
        }

        System.out.println("password 일치");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getNickname(), dto.getPassword());

        System.out.println("authenticationToken: " + authenticationToken);

        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println("authentication: " + authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(authentication, 60*60*1000L);
        System.out.println("최종 accessToken: " + accessToken);
        return new MemberLogInResponse(accessToken);
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
    @Transactional
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        System.out.println("loadUserByUsername 유저 찾음: " + member);
        return member;
    }

}
