package security.corespringsecurity.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.corespringsecurity.security.common.FormWebAuthenticationDetails;
import security.corespringsecurity.security.service.AccountContext;
import security.corespringsecurity.security.token.AjaxAuthenticationToken;

public class AjaxAuthenticationProvider implements AuthenticationProvider {
//      사실상 폼인증 방식과 같다.
//      다른점은 역시 인증토큰이 Ajax용으로만든 토큰이라는 점이다.

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    커스텀 검증하기위한 클래스
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        authentication객체는 매니저로부터 전달받은 인증객체로 사용자의 아이디와 패스워드 정보가 담겨져있다.

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

//        우리가 설정한 커스텀클래스로 받기 우선 이름 검사
        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

//        비밀번호 검사
//        사용자로부터 입력받은 authentication 정보와 AccountContext객체가 일치하는지 여부 확인
        if (!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            System.out.println("비번 노일치");
            throw new BadCredentialsException("BadCredentialException");
        }

//        인증객체 생성
        AjaxAuthenticationToken authenticationToken
                = new AjaxAuthenticationToken(accountContext.getAccount(),
                null,
                accountContext.getAuthorities());
//          자신을 호출한 provider매니저에게 리턴
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AjaxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
