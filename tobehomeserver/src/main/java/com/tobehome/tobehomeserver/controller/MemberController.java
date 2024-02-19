package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.MemberDTO;
import com.tobehome.tobehomeserver.dto.request.member.MemberLogInRequest;
import com.tobehome.tobehomeserver.dto.request.member.MemberSignInRequest;
import com.tobehome.tobehomeserver.dto.request.member.UpdateNicknameRequest;
import com.tobehome.tobehomeserver.dto.response.member.MemberLogInResponse;
import com.tobehome.tobehomeserver.exception.MemberNotFoundException;
import com.tobehome.tobehomeserver.exception.NicknameDuplicateException;
import com.tobehome.tobehomeserver.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /*
        로그인/회원가입 관련 API
     */
    @PostMapping("signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberSignInRequest request) {
        Long id = memberService.signup(request);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.created(null).body(response);
    }

    @PostMapping("login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberSignInRequest request) {
//        String token = memberService.login(request);
//        Map<String, Object> response = new HashMap<>();
//        response.put("token", token);
//        return ResponseEntity.ok(response);
//    }
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberLogInRequest request) {
        Map<String, Object> response = memberService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMemberInfo(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);
        if (member != null) {
            MemberDTO memberDTO = MemberDTO.from(member);
            return ResponseEntity.ok(memberDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{memberId}/nickname")
    public ResponseEntity<String> updateNickname(@PathVariable Long memberId, @RequestBody UpdateNicknameRequest request) {
        try {
            memberService.updateNickname(memberId, request.getNewNickname());
            return ResponseEntity.ok("닉네임이 성공적으로 수정되었습니다.");
        } catch (NicknameDuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (MemberNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("닉네임 수정 중 오류가 발생했습니다.");
        }
    }


//    @GetMapping("logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response,
//                SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/";
//    }

}
