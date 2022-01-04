package freemarkerTest.demo.controller;

import freemarkerTest.demo.domain.UriTest;
import freemarkerTest.demo.domain.UriTest2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class UriController {

    @GetMapping("/uriTest")
    public String uri() {
        return "uriTrim/index";
    }

    @GetMapping("/jsonTest")
    public String json() {
        return "json/index";
    }

    @GetMapping("/jsonIndex")
    public String json2() {
        return "json/index2";
    }
    @PostMapping("/jsonParsing")
    public String json3(Model model, UriTest uriTest) {
        model.addAttribute("value", uriTest.getUri());
        model.addAttribute("number", uriTest.getNumber());
        return "json/jsonParsing";
    }

    @PostMapping("/jsonParsing2")
    public String json4(Model model, UriTest2 uriTest) {
        model.addAttribute("uri", uriTest.getUri());
        model.addAttribute("param", uriTest.getParam());
        model.addAttribute("value", uriTest.getValue());

        return "json/jsonParsing2";
    }



}
