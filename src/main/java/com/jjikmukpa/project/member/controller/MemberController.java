package com.jjikmukpa.project.member.controller;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String register() { return "layout/member/signup"; }

    @PostMapping("/register")
    public String register(SignupDTO signupDTO) {
        memberService.register(signupDTO);

        return "redirect:/";
    }
}
