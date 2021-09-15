package security.corespringsecurity.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import security.corespringsecurity.repository.AccessIpRepository;
import security.corespringsecurity.security.common.FormAuthenticationDetailsSource;
import security.corespringsecurity.security.factory.UrlResourcesMapFactoryBean;
import security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import security.corespringsecurity.security.filter.PermitAllFilter;
import security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import security.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler;
import security.corespringsecurity.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import security.corespringsecurity.security.provider.CustomAuthenticationProvider;
import security.corespringsecurity.security.service.RoleHierarchyServiceImpl;
import security.corespringsecurity.security.voter.IpAddressVoter;
import security.corespringsecurity.service.SecurityResourceService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
// 메서드 방식 권한설정을하기위한 설정
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
@Order(1)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private FormAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityResourceService securityResourceService;


    private String[] permitAllResources = {"/", "/login", "/user/login/**"};

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

                .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)

        ;
    }

//    에러 경로 설정
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();

        accessDeniedHandler.setErrorPage("/denied");

        return accessDeniedHandler;
    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {

        PermitAllFilter filterSecurityInterceptor = new PermitAllFilter(permitAllResources);
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadatasource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        return filterSecurityInterceptor;

    }

    @Bean
    public AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecistionVoter());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<?>> getAccessDecistionVoter() {

        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
//        Ip먼저 심사하도록 설정
        accessDecisionVoters.add(new IpAddressVoter(securityResourceService));
        accessDecisionVoters.add(roleVoter());

        return accessDecisionVoters;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {

        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHirerarchy());

        return roleHierarchyVoter;
    }

    @Bean
    public RoleHierarchyImpl roleHirerarchy() {

        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();

        return roleHierarchy;
    }


    //    생성한 db연동을 위해 맵핑을한다.
    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadatasource() throws Exception {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), securityResourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {

        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);

        return urlResourcesMapFactoryBean;
    }
}
