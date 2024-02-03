package com.tobehome.tobehomeserver.controller;

import com.tobehome.tobehomeserver.domain.entity.Member;
import com.tobehome.tobehomeserver.dto.request.member.MemberRequestDto;
import com.tobehome.tobehomeserver.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Lazy
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(MemberRequestDto request) {
        memberService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

}
