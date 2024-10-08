package com.jjikmukpa.project.config;

import com.jjikmukpa.project.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    // security에서 특정 경로를 보안 검증에서 제외하는 코드
    // CSS, JS, 이미지 같은 정적 자원들에 대해 보안 필터를 적용하지 않게 함
    @Bean
    public WebSecurityCustomizer securityCustomizer(){
        return new WebSecurityCustomizer() {
            @Override
            public void customize(WebSecurity web) {
                web.ignoring().requestMatchers("/css/**", "/js/**", "/image/**");
            }
        };
    }
//    이미지 적용이 안 돼서 아래 코드에서 위 코드로 변경함
//            return (web) -> web.ignoring()
//            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());


    // Spring Security에서 제공하는 인증, 인가를 위한 필터들의 모음
    // 기본적으로 제공하는 필터들이 있고, 커스텀 필터 또한 적용시킬 수 있다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/","/index.html", "/layout/main/main.html").permitAll()   // 모두에게 허용
                    .requestMatchers("/member/register").anonymous()    // 회원가입은 비인증 사용자만 접근
                    .requestMatchers("/auth/login").anonymous()
                    .requestMatchers("/member/checkid").anonymous()
                    .requestMatchers("/member/checknickname").anonymous()
                    .requestMatchers("/member/checkuser").anonymous()
                    .requestMatchers("/member/checkstatus").anonymous()
                    .requestMatchers("/member/findId").anonymous()
                    .requestMatchers("/member/findPw").anonymous()
                    .requestMatchers("/member/changePwNotLoggedIn").anonymous()
                    .requestMatchers("/post/postList","/post/detailPost/**").permitAll()
                    .requestMatchers("/post/createPost","/post/modifyPost/**","/post/delete/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")      // ROLE이 ADMIN인 경우만 접근 가능
                    .requestMatchers("/error/accessDenied").permitAll() // 접근 거부 페이지 허용
                    .requestMatchers("/member/mypage").authenticated()
                    .requestMatchers("/sendMail").permitAll()
                    .requestMatchers("/checkMail").permitAll()
                    .anyRequest().authenticated();  // 인증된 사용자만 요청 가능
        }));

        // formLogin 설정
        http.formLogin(formLoginConfigurer -> {
                formLoginConfigurer
                    .loginPage("/auth/login")           // 로그인 페이지 (GET)
                    .loginProcessingUrl("/auth/login")  // 로그인 처리 (POST)
                    .usernameParameter("memberId")      // userName으로 전달할 파라미터 설정
                    .passwordParameter("memberPw")      // password로 전달할 파라미터 설정
                    .defaultSuccessUrl("/")             // 로그인 성공 시 이동할 url
                    .failureUrl("/auth/login?error=true");
//                    .failureHandler(customAuthenticationFailureHandler)
//                    .permitAll();
            });

//        http.addFilterBefore(new CustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.logout(logoutConfigurer -> {
            logoutConfigurer.logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/");     // 로그아웃 후 메인페이지로 이동
        });

        // 접근 거부 핸들러 설정
        http.exceptionHandling(exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer
                        .accessDeniedPage("/layout/error/accessDenied")
        );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}