package freemarkerTest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClickController {

    @GetMapping("/click")
    public String click() {
        return "click/index";
    }
    @GetMapping("/click2")
    public String click2() {
        return "click/index2";
    }
}
