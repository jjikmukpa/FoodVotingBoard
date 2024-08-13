package com.jjikmukpa.project.member.service;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.model.entity.RoleType;
import com.jjikmukpa.project.member.model.entity.Status;
import com.jjikmukpa.project.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(SignupDTO signupDTO) {
        Member member = Member.builder()
                .memberId(signupDTO.getMemberId())
                .memberPw(passwordEncoder.encode(signupDTO.getMemberPw()))  // 암호화
                .name(signupDTO.getName())
                .nickname(signupDTO.getNickname())
                .email(signupDTO.getEmail())
                .dateOfBirth(signupDTO.getDateOfBirth())
                .phone(signupDTO.getPhone())
                .address(signupDTO.getAddress())
                .createdDate(LocalDateTime.now())
                .status(Status.valueOf(signupDTO.getStatus()))  // ACTIVATED, DEACTIVATED,
                .role(RoleType.valueOf(signupDTO.getRole()))    // USER, ADMIN
                .build();

        Member savedMember = memberRepository.save(member);
    }

    public Member findMemberById(String memberId) {
        Member member = memberRepository.findMemberByMemberId(memberId)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));

        return member;
    }

    public String findMemberIdByNameAndEmail(String name, String email) {
        Member member = memberRepository.findMemberByNameAndEmail(name, email)
                .orElseThrow(() -> new NoSuchElementException("No member found with the provided name and email"));
        return member.getMemberId();
    }

    public String findMemberIdByNameAndPhone(String name, String phone) {
        Member member = memberRepository.findMemberByNameAndPhone(name, phone)
                .orElseThrow(() -> new NoSuchElementException("No member found with the provided name and phone"));
        return member.getMemberId();
    }

    public boolean existsMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    public boolean existsNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean existsPhone(String phone) {
        return memberRepository.existsByPhone(phone);
    }

    public Status getMemberStatus(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        if (member != null) {
            return member.getStatus();
        }
      
        return Status.UNKNOWN;
    }

    /* mypage 작업 영역 */
    public Member getLoggedInMember() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findMemberByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public boolean checkPassword(String memberId, String rawPassword) {
        Member member = findMemberById(memberId);
        return passwordEncoder.matches(rawPassword, member.getMemberPw());
    }

    @Transactional
    public void changePassword(String memberId, String newPassword) {
        Member member = findMemberById(memberId);
        member.setMemberPw(passwordEncoder.encode(newPassword));
        save(member);
    }

}
