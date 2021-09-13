package security.corespringsecurity.controller.login;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        return "user/login/login";
    }

//    로그아웃하기위해서는 우리가 s.c 로그아웃 핸들러클래스사용
//    3개파라미터를 받음 request, response, 인증객체(s.c저장된)
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
//            로그아웃 필터에 존재
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/login";
    }
}
