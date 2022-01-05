package com.example.FreemarkerShop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloFreemarker {

    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }
}
