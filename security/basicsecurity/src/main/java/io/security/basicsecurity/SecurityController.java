package io.security.basicsecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class SecurityController {

//    @GetMapping("/")
//    public String index() {
//        return "home";
//    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/pay")
    public String adminPay() {
        return "adminPay";
    }

    @GetMapping("/admin/**")
    public String admin() {
        return "admin";
    }

    @GetMapping("/denied")
    public String denied() {
        return "Access is denied";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index(HttpSession session) {
//      s.c는 세션에도 저장되고 인증된 사용자가 인증이후에 사이트 접속할때는 세션에 저장된 s.c객체를 가져와 다시 threadLocal에 저장
//        한다. 아래의 코드를 통해 같은지 비교해서 알수있다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

        Authentication authentication1 = context.getAuthentication();
        return "home";
    }

    @GetMapping("/thread")
    public String thread() {

//        메인스레드에 s.c가 담겨있지 자식스레드에는 s.c가 담겨지있지 않기때문에 null이 나오고 만약 모드를 바꾼다면 존재한다.
//        MODE_THREADLOCAL, MODE_INHERITABLETHREADLOCAL
//        configure에 SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)로 두면
//        자식스레드도 s.c 참조 가능 
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    }
                }
        ).start();

        return "thread";
    }
}
