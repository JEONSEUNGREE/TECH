package com.example.jwttutorial.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
//                        sql문에 자동등록할때 안걸리게
                        "/h2-console/**"
                        , "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                http 서블릿 리퀘스트를 사용하는 요청들의 경우 접근 제한을 설정하겠다.
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated();
//                antMatcher부분만 허용한하고 다른부분의 경우 제한



    }
}
