package com.jjikmukpa.project.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
//    private final MemberService memberService;
//
//    public AuthController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/login")
    public String login(){ return "/layout/auth/login"; }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String memberId, @RequestParam String memberPw) {
//        Member member = memberService.findMemberById(memberId);
//
//        if (member == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 다릅니다.");
//        }
//
//        if ("DELETED".equals(member.getStatus().name())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제된 회원입니다.");
//        }
//
//        // Proceed with further login steps (e.g., password check, session creation, etc.)
//
//        return ResponseEntity.ok("로그인 성공");
//    }
}