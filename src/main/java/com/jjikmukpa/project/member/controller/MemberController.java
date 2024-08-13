package com.jjikmukpa.project.member.controller;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.model.entity.Status;
import com.jjikmukpa.project.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jjikmukpa.project.member.model.entity.Member;

import java.time.LocalDateTime;
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

    @GetMapping("/findPw")
    public String findPw() {
        return "layout/member/findPw";
    }

    @PostMapping("/findPw")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> findPw(@RequestParam(required = false) String id1,
                                                       @RequestParam(required = false) String name1,
                                                       @RequestParam(required = false) String name2,
                                                       @RequestParam(required = false) String id2,
                                                       @RequestParam(required = false) String email,
                                                       @RequestParam(required = false) String phone,
                                                       @RequestParam String searchBy) {
        Map<String, Boolean> response = new HashMap<>();

        try {
            boolean exists = false;
            if ("email".equals(searchBy)) {
                exists = memberService.existsByMemberIdAndNameAndEmail(id2, name2, email);
            } else if ("phone".equals(searchBy)){
                exists = memberService.existsByMemberIdAndNameAndPhone(id1, name1, phone);
            }

            response.put("exists", exists);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            response.put("error", false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/checkstatus")
    public ResponseEntity<Map<String, String>> checkStatus(
            @RequestParam String memberId) {
        String status = memberService.getMemberStatus(memberId) + "";

        Map<String, String> response = new HashMap<>();
        response.put("status", status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/changePwNotLoggedIn")
    public String changePwNotLoggedIn(@RequestParam String id, Model model) {
        model.addAttribute("id", id);

        return "layout/member/changePwNotLoggedIn";
    }

    @PostMapping("/changePwNotLoggedIn")
    public ResponseEntity<Map<String, String>> changePwNotLoggedIn(
                                        @RequestParam String userId,
                                        @RequestParam String modifiedPw,
                                        @RequestParam String modifiedPwConfirm) {
        Map<String, String> response = new HashMap<>();

        // 서버사이드에서도 확인
        if (modifiedPw == null || modifiedPwConfirm == null || !modifiedPw.equals(modifiedPwConfirm)) {
            response.put("error", "비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        String regexPw = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        if (!modifiedPw.matches(regexPw)) {
            response.put("error", "비밀번호는 최소 8자 이상이어야 하며, 적어도 하나의 문자, 숫자 및 특수 문자를 포함해야 합니다.");
            return ResponseEntity.badRequest().body(response);
        }

        if (memberService.isPasswordInUse(userId, modifiedPw)) {
            log.info("🌊🌊🌊🌊🌊🌊🌊🌊🌊 Existing password is being reused.");
            response.put("error", "기존과 같은 비밀번호는 사용하실 수 없습니다.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            memberService.updatePassword(userId, modifiedPw);
            response.put("success", "비밀번호가 성공적으로 변경되었습니다.");
        } catch (NoSuchElementException e) {
            response.put("error", "존재하지 않는 회원입니다.");
        }

        return ResponseEntity.ok(response);
    }
  
    /* mypage 작업 영역 */
    @GetMapping("/mypage")
    public String myPage() {
        return "layout/member/mypage"; }

    @GetMapping("/info")
    public String memberInfo(Model model) {
        Member member = memberService.getLoggedInMember();
        model.addAttribute("member", member);
        return "layout/member/info";
    }

    @GetMapping("/delete")
    public String deletedMember() {
        return "layout/member/mypage";
    }

    @PostMapping("/delete")
    public String deleteMember(HttpServletRequest request) {
        // 현재 로그인한 사용자 정보를 가져오기
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 사용자 정보를 가져와서 상태를 'deleted'로 변경
        Member member = memberService.findMemberById(memberId);
        if (member != null) {
            member.setStatus(Status.DELETED);
            member.setDeletedDate(LocalDateTime.now());
            memberService.save(member);
            log.info("Member with username '{}' has been deleted.", memberId);
        }

        // 현재 인증된 사용자 세션 무효화 및 로그아웃 처리
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();  // 세션 무효화

//        // 현재 인증된 사용자 세션 무효화 및 로그아웃 처리
//        SecurityContextHolder.clearContext();
//        HttpSession session = request.getSession(false); // 기존 세션 가져오기
//        if (session != null) {
//            session.invalidate(); // 세션 무효화
//        }

        // 회원 탈퇴 처리 후 홈으로 돌아가기
        return "redirect:/";
    }

    @GetMapping("/re_nickname")
    public ResponseEntity<Map<String, Boolean>> re_nickname(@RequestParam String nickname) {
        boolean exists = memberService.existsNickname(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/changePw")
    public String changePw() {
        return "layout/member/changePw"; 
    }
}
