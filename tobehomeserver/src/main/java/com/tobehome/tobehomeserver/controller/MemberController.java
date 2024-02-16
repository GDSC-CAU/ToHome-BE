package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.dto.request.member.MemberLogInRequest;
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

//    @GetMapping("logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response,
//                SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/";
//    }

}
