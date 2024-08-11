package com.jjikmukpa.project.member.controller;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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

    @GetMapping("/mypage")
    public String myPage() {
        return "layout/member/mypage";
    }

    @GetMapping("/findId")
    public String findId() {
        return "layout/member/findId";
    }

    @PostMapping("/findId")
    @ResponseBody
    public ResponseEntity<Map<String, String>> findId(@RequestParam(required = false) String name1,
                                                      @RequestParam(required = false) String name2,
                                                      @RequestParam(required = false) String email,
                                                      @RequestParam(required = false) String phone,
                                                      @RequestParam String searchBy) {
        Map<String, String> response = new HashMap<>();

        try {
            String memberId = "";
            if ("email".equals(searchBy)) {
                memberId = memberService.findMemberIdByNameAndEmail(name2, email);
            } else if ("phone".equals(searchBy)){
                memberId = memberService.findMemberIdByNameAndPhone(name1, phone);
            }

            response.put("memberId", memberId);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            response.put("error", "아이디를 찾을 수 없습니다.\n입력하신 정보를 다시 확인해주세요.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
