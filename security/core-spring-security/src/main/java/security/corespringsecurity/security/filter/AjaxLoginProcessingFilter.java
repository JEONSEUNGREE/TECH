package security.corespringsecurity.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import security.corespringsecurity.domain.AccountDto;
import security.corespringsecurity.security.token.AjaxAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    //    json방식으로 올 요청
    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter() {
//        사용자가 /api/login 에 요청이 오면 실행되는 필터
        super(new AntPathRequestMatcher("/api/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!isAjax(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

//        json형식으로 받은 값을 AccountDto로 받는다.
        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);

//        null 예외처리
        if (StringUtils.hasText(accountDto.getUsername()) || StringUtils.hasText(accountDto.getPassword())) {
            throw new IllegalArgumentException("Username or Password is empty");
        }
//      첫번째 생성자(인증받기위한 객체)
        AjaxAuthenticationToken ajaxAuthenticationToken =
                new AjaxAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());

//        매니저에게 인증하도록 전달 -> 매니저 -> 프로바이더
//        이후에 과정은 정상적이지 못하다.
//        filter안에서 실제 인증하는 provider가 Ajax용이 아니라 Form인증방식이라서
//        따로 클래스를 생성해야한다.
//        토큰타입또한 틀리기때문에 작동하지않음
        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

//    Ajax인지 아닌지 여부 확인
//    클라이언트 쪽과의 정해놓은 포맷으로
    private boolean isAjax(HttpServletRequest request) {

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-with"))) {
            return true;
        }
        return false;
    }
}
