package com.jjikmukpa.project.member.controller;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/checkid")
    public ResponseEntity<Map<String, Boolean>> checkMemberId(@RequestParam String memberId) {
        boolean exists = memberService.existsMemberId(memberId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/checknickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean exists = memberService.existsNickname(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/checkuser")
    public ResponseEntity<Map<String, Boolean>> checkUser(
            @RequestParam String email,
            @RequestParam String phone) {
        boolean emailExists = memberService.existsEmail(email);
        boolean phoneExists = memberService.existsPhone(phone);

        Map<String, Boolean> response = new HashMap<>();

        response.put("emailExists", emailExists);
        response.put("phoneExists", phoneExists);

        return ResponseEntity.ok(response);
    }
}
