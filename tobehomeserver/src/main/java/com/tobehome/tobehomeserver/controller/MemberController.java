package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.dto.request.member.MemberSignInRequest;
import com.tobehome.tobehomeserver.dto.response.member.MemberLogInResponse;
import com.tobehome.tobehomeserver.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberSignInRequest request) {
        Long id = memberService.signup(request);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.created(null).body(response);
    }

    @PostMapping("login")
    public MemberLogInResponse login(@RequestBody MemberSignInRequest request) {
        return memberService.login(request);
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

}