package freemarkerTest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UriController {

    @GetMapping("/uriTest")
    public String uri() {
        return "uriTrim/index";
    }

    @GetMapping("/jsonTest")
    public String json() {
        return "json/index";
    }



}
