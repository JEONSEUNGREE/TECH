package io.security.basicsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    UserDetailsService userDetailsService;

//    AuthenticationManagerBuilder클래스는 사용자를 생성하고 권한을 부여할수있도록 한다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//              {noop} 패스워드암호화시 스프링시큐리티5버전부터 암호화방식(free fix)정해야한다. 나중에검사시 방식을통해서 검사하기때문에
//               적용하지않는경우 아이디가 null로 나옴
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("USER"); //- 메모리방식
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1234").roles("SYS","USER"); //- 메모리방식
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","SYS","USER"); //- 메모리방식
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        스프링시큐리티가 초기화해주는것과 유사하게 일단 작성
        http
                .authorizeRequests()
//                요청에대한 보안검사상태 어떠한 요청에도 인증을받도록 설정 (인가 정책)
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin/pay").hasRole("ADMIN") //admin/pay가 admin/**위에있으면 sys가 접근이 불가하지만
                                                                        // 위치가 바뀌면 sys도 접근한다 위에가 더 넓어 밑에를 포함해버리기때문에
                                                                        // 그래서 구체적인 url이 앞에 나와야한다.
                .antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('SYS')")
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
//                .deleteCookies("remember-me");
                .and()
                //                리멤버미 설정
                .rememberMe()
                .rememberMeParameter("remember")
//                default는 remember-me이고 파라미터를 위와같이 변경할수있다.
//                토큰의 기본시간은 14일이고 초단위로 재설정할수있다.
                .tokenValiditySeconds(3600)
//                리멤버처리시 유저계정을 조회하는데 필요한 클래스
                .userDetailsService(userDetailsService);

        http
                .sessionManagement()
                .sessionFixation().none()
//                .sessionCreationPolicy() 세션정책
//        SessionCreationPolicy. Always 		:  스프링 시큐리티가 항상 세션 생성
//        SessionCreationPolicy. If_Required 		:  스프링 시큐리티가 필요 시 생성(기본값)
//        SessionCreationPolicy. Never   		:  스프링 시큐리티가 생성하지 않지만 이미 존재하면 사용
//        SessionCreationPolicy. Stateless	 	:  스프링 시큐리티가 생성하지 않고 존재해도 사용하지 않음 (예를들어 jwt쓸때)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);




    }
}
