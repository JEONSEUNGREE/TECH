package freemarkerTest.demo.controller;


import freemarkerTest.demo.domain.Member;
import freemarkerTest.demo.domain.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class VueFreemarkerController {

    @RequestMapping(value = "/register")
    public String loginPage() {

        return "login/register";
    }

    @PostMapping(value = "/register-post")
    public String login(Member member) {

        log.info(member.getName());

        return "login/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginProcess(MemberDto memberDto) {
        return "/main";
    }

    @GetMapping(value = "/front")
    public String front(Model model) {
        List<String> category = new ArrayList<>();
        category.add("Home");
        category.add("T&A");
        category.add("Schedule");
        category.add("Calendar");
        category.add("Organization");

        model.addAttribute("categories", category);

        return "/home";
    }
}
