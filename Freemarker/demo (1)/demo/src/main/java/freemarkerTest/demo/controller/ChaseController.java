package freemarkerTest.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChaseController {

    @GetMapping("/main")
    public String main() {
        return "cH/index";
    }
}
