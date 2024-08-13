package com.jjikmukpa.project.config;

import com.jjikmukpa.project.member.model.entity.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Slf4j
//public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login", "POST");

    public CustomUsernamePasswordAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        String memberId = request.getParameter("memberId");
        String memberPw = request.getParameter("memberPw");
        log.info("🎀🎀🎀🎀🎀🎀🎀 checkMemberStatus(memberId): " + checkMemberStatus(memberId));

        // Custom authentication logic, check the status here
        if (checkMemberStatus(memberId) == Status.DELETED) {
            log.info("🎀🎀🎀🎀🎀🎀🎀 status: deleted");
            throw new UserDeletedException("DELETED");
//            request.getSession().setAttribute("errorMessage", "삭제된 회원입니다.");
//            response.sendRedirect("/auth/login?error=true");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(memberId, memberPw);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private Status checkMemberStatus(String memberId) {
        return Status.ACTIVATED;
    }
}