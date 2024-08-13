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
import com.jjikmukpa.project.post.model.dto.DebatePostDTO;
import com.jjikmukpa.project.post.model.dto.PostDTO;
import com.jjikmukpa.project.post.service.DebatePostService;
import com.jjikmukpa.project.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.format.DateTimeFormatter;

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

    private final PostService postService;
    private final DebatePostService debatePostService;
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

    @GetMapping("/checkstatus")
    public ResponseEntity<Map<String, String>> checkStatus(
            @RequestParam String memberId) {
        String status = memberService.getMemberStatus(memberId) + "";

        Map<String, String> response = new HashMap<>();
        response.put("status", status);
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

        // LocalDateTime을 String으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedCreatedDate = member.getCreatedDate().format(formatter);
        model.addAttribute("formattedCreatedDate", formattedCreatedDate);
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

        // 회원 탈퇴 처리 후 홈으로 돌아가기
        return "redirect:/";
    }

    // 비밀번호 변경 페이지를 반환하는 메서드
    @GetMapping("/changePw")
    public String changePw() {
        return "layout/member/changePw"; } // 뷰 파일 경로

    // 비밀번호 변경 요청을 처리하는 메서드
    @PostMapping("/changePw")
    public String changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String nowPw,
                                 @RequestParam String afterPw, RedirectAttributes redirectAttributes) {
        String memberId = userDetails.getUsername();
        if (!memberService.checkPassword(memberId, nowPw)) {
            redirectAttributes.addFlashAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
            return "redirect:/member/changePw";
        }

        memberService.changePassword(memberId, afterPw);
        return "redirect:/member/info";
    }

    // 비밀번호 확인을 위한 API 엔드포인트
    @PostMapping("/checkPassword")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkPassword(@RequestBody Map<String, String> request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        String nowPw = request.get("nowPw");
        boolean valid = memberService.checkPassword(memberId, nowPw);
        Map<String, Boolean> response = new HashMap<>();
        response.put("valid", valid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/myPosts")
    public String getMyPosts(@AuthenticationPrincipal UserDetails userDetails, Pageable pageable, Model model) {
        String memberId = userDetails.getUsername();
        Member member = memberService.findMemberById(memberId);

        Page<PostDTO> myPosts = postService.findPostsByMember(member, pageable);
        Page<DebatePostDTO> myDebatePosts = debatePostService.findDebatePostsByMember(member, pageable);

        model.addAttribute("postList", myPosts);
        model.addAttribute("debatePostList", myDebatePosts);

        return "layout/member/myPosts";
    }
}
