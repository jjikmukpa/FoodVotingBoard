package com.jjikmukpa.project.member.service;

import com.jjikmukpa.project.member.model.dto.SignupDTO;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.model.entity.RoleType;
import com.jjikmukpa.project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
                .status(signupDTO.getStatus())
                .role(RoleType.valueOf(signupDTO.getRole()))    // USER, ADMIN
                .build();

        Member savedMember = memberRepository.save(member);
    }
}
