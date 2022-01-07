package freemarkerTest.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test/index")
    public String test() {
        return "test/test";
    }
    @GetMapping("/test/index1")
    public String test1() {
        return "test/test1";
    }

    @GetMapping("/test/index2")
    public String test2(Model model) {
        return "test/builtInLibrary";
    }
}
