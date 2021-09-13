package security.corespringsecurity.security.common;


import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class FormWebAuthenticationDetails extends WebAuthenticationDetails {
//  사용자가 전달하는 추가 파라미터 값 저장 클래스

    private String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
//        secret_key가 가입시 받은 파라미터와 정확히 일치하도록 주의
        secretKey = request.getParameter("secret_key");
    }

    public String getSecretKey() {
        return secretKey;
    }
}
