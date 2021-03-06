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
// ????????? ?????? ??????????????????????????? ??????
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
//        customAuthenticationProvider?????? ??????????????????????????? ?????? ????????????
//        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
//      ????????? ???????????? ????????? ?????? ???????????? ?????????
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
//        ??????????????? ????????? ?????????????????? ?????? ?????? ????????????
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
//                ????????????????????? ???????????? url
                .authenticationDetailsSource(authenticationDetailsSource)
//                ?????????????????? ?????? ??????
//                <form th:action="@{/login_proc}" class="form-signin" method="post">
                .defaultSuccessUrl("/")
//                custom??? ??????url??????
                .successHandler(customAuthenticationSuccessHandler)
//                ?????? ?????????
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())

                .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)

        ;
    }

//    ?????? ?????? ??????
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
//        Ip?????? ??????????????? ??????
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


    //    ????????? db????????? ?????? ???????????????.
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
