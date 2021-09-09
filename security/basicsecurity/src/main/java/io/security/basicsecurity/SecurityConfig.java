package io.security.basicsecurity;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration //설정하기위한 어노테이션
@EnableWebSecurity // 여러 클래스를 임폿해서 실행시키는 어노테이션
// @Import({ WebSecurityConfiguration.class, SpringWebMvcImportSelector.class, OAuth2ImportSelector.class,
//		HttpSecurityConfiguration.class }) 다음과같은 클래스들이 임폿되있음
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        스프링시큐리티가 초기화해주는것과 유사하게 일단 작성
        http
                .authorizeRequests()
//                요청에대한 보안검사상태 어떠한 요청에도 인증을받도록 설정 (인가 정책)
                .anyRequest().authenticated();

        http
                .formLogin()    //spring.security.user.name과 password를 설정한다.
//                .loginPage("/loginPage")  //스프링부트 시큐리티제공 ui말고 개인적으로 커스텀한 ui를 쓰고싶은경우
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_proc")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        Authentication 인증했을때 최종적으로 넘어오는 값
                        System.out.println("authentication " + authentication.getName());
//                       인증에 성공한 유저 이름
                        response.sendRedirect("/");
//                        로그인하고 연결되는 주소
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                        인증예외시 (실패시)
                        System.out.println("exception " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll();
//                loginPage("/loginPage") 이부분은 로그인하기위한 페이지이기때문에 접근이 가능하도록 해야해서 permitall로 둔다.

        http
                .logout()
                .logoutUrl("logout")
//                원칙적으로는 로그아웃은 스프링시큐리티가 post방식으로 한다 하지만 get방식으로도 설정할수있음(추후)
                .logoutSuccessUrl("/login")
//                로그아웃성공시 /login이동
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate();
//                        세션 무효화 작업
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
//                logoutSuccessUrl과 다른점은 logoutSuccessHandler와 다르게 다양한 로직 구성가능
//                logoutSuccessUrl은 단지 url이동만 가능
                    }
                })
//                remember-me인증시 서버에서 쿠키발급 따라서 로그아웃시 삭제해야함
                .deleteCookies("remember-me")
                ;
    }
}
