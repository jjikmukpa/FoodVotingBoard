package com.jjikmukpa.project.auth.service;

import com.jjikmukpa.project.auth.principal.AuthPrincipal;
import com.jjikmukpa.project.member.model.entity.Member;
import com.jjikmukpa.project.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthPrincipal(member);
    }
}
