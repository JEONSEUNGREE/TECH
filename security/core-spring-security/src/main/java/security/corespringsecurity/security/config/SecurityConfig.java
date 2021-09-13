package security.corespringsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.corespringsecurity.security.common.FormAuthenticationDetailsSource;
import security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import security.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler;
import security.corespringsecurity.security.provider.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private FormAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        customAuthenticationProvider에서 사용하고있기때문에 이제 필요없음
//        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
//      빈으로 만들어야 우리가 만든 커스텀을 참조함
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//   Ajax용 필터
//    매니저빈 생성해서 주입후 리턴
//    현재 csrf가 활성화 되어있기때문에
//    포스트 방식으로 요청시 반드시 csrf토큰을 가지고 가야한다.
    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return ajaxLoginProcessingFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        정적파일이 필터에 걸리지않도록 하는 설정 비용절감
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users", "user/login/**", "/login*").permitAll()
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
//                추가파라미터를 받기위한 url
                .authenticationDetailsSource(authenticationDetailsSource)
//                로그인페이지 아래 부분
//                <form th:action="@{/login_proc}" class="form-signin" method="post">
                .defaultSuccessUrl("/")
//                custom한 요청url작업
                .successHandler(customAuthenticationSuccessHandler)
//                실패 핸들러
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
//                ajax 필터 요청
                .and()
                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
//                addFilterBefore 추가하고자하는 필터가 기존필터앞쪽에 위치
//                addFilter     필터 맨마지막에 위치
//                addFilterAfter 기존필터 뒤쪽에
//                addFilterAt 기존필터 위치를 대체하고자 할때
        ;
    }

//    에러 경로 설정
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();

        accessDeniedHandler.setErrorPage("/denied");

        return accessDeniedHandler;
    }
}
