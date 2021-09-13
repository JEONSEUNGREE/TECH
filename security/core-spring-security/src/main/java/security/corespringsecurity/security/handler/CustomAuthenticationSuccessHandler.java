package security.corespringsecurity.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

//    이전에 사용자가 가져왔던 요청에 관련된 정보를 참조하기위한 객체
//    사용자가 로그인성공전에 거쳐온 여러정보
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

//        기본 url설정
        setDefaultTargetUrl("/");

//        null일수있음 이전 요청 url 이 없는경우 (단순히 로그인한경우)
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
//            요청 url로 이동
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
//            요청 url 없는경우 기본url로 보내기
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
