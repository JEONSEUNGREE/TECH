package security.corespringsecurity.controller.user;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import security.corespringsecurity.domain.Account;
import security.corespringsecurity.domain.AccountDto;
import security.corespringsecurity.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/mypage")
    public String myPage() throws Exception {
        return "/user/mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto) {

//        모델맵퍼를 통해 dto를 account로 매핑되도록함
        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(accountDto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        userService.createUser(account);

        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal Account ao
            , Authentication authenticaion, Principal principal) {

        userService.order();

        return "user/mypage";
    }

}
