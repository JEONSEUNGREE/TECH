package security.corespringsecurity.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.corespringsecurity.security.common.AjaxAccessDeniedHanlder;
import security.corespringsecurity.security.common.AjaxLoginAuthenticationEntryPoint;
import security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import security.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler;
import security.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import security.corespringsecurity.security.provider.AjaxAuthenticationProvider;

@Configuration
//  어느설정클래스를 먼저초기화할지 모르기에 오류발생주의 -순서필수- (WebSecurityConfigurerAdapter 2개임)
@Order(0)
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AjaxAuthenticationProvider ajaxAuthenticationProvider() {
        return new AjaxAuthenticationProvider();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider());
    }

    @Bean
    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHanlder() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/messages").hasRole("MANAGER")
                .anyRequest().authenticated();
//                ajax 필터 요청
//                .and()
//                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
//                .accessDeniedHandler(ajaxAccessDeniedHandler());
        http.csrf().disable();
//                addFilterBefore 추가하고자하는 필터가 기존필터앞쪽에 위치
//                addFilter     필터 맨마지막에 위치
//                addFilterAfter 기존필터 뒤쪽에
//                addFilterAt 기존필터 위치를 대체하고자 할때
        customConfigurerAjax(http);
    }

    private void customConfigurerAjax(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHanlder())
                .failureHandlerAjax(authenticationFailureHandler())
                .setAuthenticationManager(authenticationManagerBean())
                .loginProcessingUrl("/api/login");
    }

    private AccessDeniedHandler ajaxAccessDeniedHandler() {
        return new AjaxAccessDeniedHanlder();
    }

    //   Ajax용 필터
//    매니저빈 생성해서 주입후 리턴
//    현재 csrf가 활성화 되어있기때문에
//    포스트 방식으로 요청시 반드시 csrf토큰을 가지고 가야한다.
//    @Bean
//    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
//        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
//        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
////        SuccessHanlder,FilureHanlder설정
//        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHanlder());
//        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
//        return ajaxLoginProcessingFilter;
//    }
}
