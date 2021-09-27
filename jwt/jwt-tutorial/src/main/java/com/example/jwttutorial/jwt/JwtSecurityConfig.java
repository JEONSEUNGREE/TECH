package com.example.jwttutorial.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;
//  커스텀한 tokenProvider 주입 받기

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
//  그리고 configure를 통해 커스텀 필터를 등록해준다(커스텀 필터안에는 우리가 설정한 jwtTokenProvider를)

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
//        물론 여기서도 addFilter의 옵션이 before, after, at..이 있는데 우선적으로 인증방식을 설정한다.
//        before를 통해서 customFilter를 default필터인 UsernamePasswordAuthenticationFilter의 앞에둔다.
//        걱정되면 debug해서 필터 목록을 찍어보면될듯싶다.
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}