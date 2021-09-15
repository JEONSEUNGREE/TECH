package security.corespringsecurity.aopsecurity;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import security.corespringsecurity.domain.AccountDto;

import java.security.Principal;

@Controller
public class AopSecurityDController {

    @GetMapping("/preAuthorize")
//    principal 인증된사용자의 인증객체 받기위함
//    home.html에서 preAtuorize에 username='user'로 설정했기때문에 파라미터는 user고 이부분의 일치여부를 확인한다.
//    url방식에서는 따로 인가처리를 하지않아서 null이라 통과된다.(AbstractSecurityInterceptor클래스 - attributes == null)
//    또한 인증없이 로그인하면 ROLE_USER는 false account.username도 null principal.username도 null이어서 false인데 통과가된다.
//    @EnableGlobalMethodSecurity의 속성이 활성화되지않아서 그렇다. 이부분을 활성화해주자.
    @PreAuthorize("hasRole('ROLE_USER') and #account.username == principal.username")
    public String preAuthorize(AccountDto accountDto, Model model, Principal principal) {

        model.addAttribute("method", "Success @PreAuthorize");

        return "aop/method";
    }
}
