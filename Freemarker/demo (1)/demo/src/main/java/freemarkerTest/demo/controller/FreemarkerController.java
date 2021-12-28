package freemarkerTest.demo.controller;


import freemarkerTest.demo.dto.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class FreemarkerController {

    @GetMapping("/index")
    public String index() {
        return "freemarker/index";
    }

    @PostMapping("/test")
    public ResponseEntity<List<String>> test(@RequestBody Test test) {
        log.info("name : " + test.getName());
        log.info("location : " + test.getLocation());
        List<String> res = new ArrayList<>();
        res.add("success");

        return new ResponseEntity<List<String>>(res,HttpStatus.OK);
    }
}
