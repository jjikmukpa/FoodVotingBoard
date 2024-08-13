package com.jjikmukpa.project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
//        log.info("🎉🎉🎉🎉🎉custom authentication failure handler");

        String errorMessage = "아이디 또는 비밀번호가 다릅니다.";

        if (exception instanceof UserDeletedException) {
            errorMessage = "삭제된 회원입니다.";
        }

//        log.info("🎉🎉🎉🎉🎉errorMessage: " + errorMessage);

        request.getSession().setAttribute("errorMessage", errorMessage);
        response.sendRedirect("/auth/login?error=true");
    }
}