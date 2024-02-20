package com.tobehome.tobehomeserver.service;

import com.tobehome.tobehomeserver.config.jwt.JwtTokenProvider;
import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.member.MemberLogInRequest;
import com.tobehome.tobehomeserver.dto.request.member.MemberSignInRequest;
import com.tobehome.tobehomeserver.dto.response.member.MemberLogInResponse;
import com.tobehome.tobehomeserver.exception.MemberNotFoundException;
import com.tobehome.tobehomeserver.exception.NicknameDuplicateException;
import com.tobehome.tobehomeserver.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;
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
    @Transactional
    public Long signup(MemberSignInRequest dto) {

        Member member = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        return memberJpaRepository.save(member).getId();
    }

    /**
     * 로그인
     */
    @Transactional
    public Map<String, Object> login(MemberLogInRequest dto) {

        Optional<Member> optionalMember = memberJpaRepository.findByNickname(dto.getNickname());

        // nickname이 일치하는 Member가 없는 경우
        if (optionalMember.isEmpty()) {
            throw new AuthenticationException("닉네임 또는 비밀번호가 존재하지 않습니다.") {};
        }

        Member member = optionalMember.get();

        // password가 일치하지 않으면 null 반환
        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new AuthenticationException("닉네임 또는 비밀번호가 일치하지 않습니다.") {};
        }


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getNickname(), dto.getPassword());


        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("id", member.getId());
        return body;
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

    /**
     * 닉네임 수정
     */
    @Transactional
    public void updateNickname(Long memberId, String newNickname, String newImageUrl) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));

        if (memberJpaRepository.existsByNickname(newNickname)) {
            throw new NicknameDuplicateException("이미 존재하는 닉네임입니다.");
        }

        member.setNickname(newNickname);
        member.setImageUrl(newImageUrl);
        memberJpaRepository.save(member);
    }

    @Transactional
    public void updateImage(Long memberId, String newImageUrl) {
        Member member = memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("회원을 찾을 수 없습니다."));

        member.setImageUrl(newImageUrl);
        memberJpaRepository.save(member);
    }


}
