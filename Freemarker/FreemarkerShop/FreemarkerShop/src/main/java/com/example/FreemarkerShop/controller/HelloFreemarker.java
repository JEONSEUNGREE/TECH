package com.example.FreemarkerShop.controller;


import com.example.FreemarkerShop.service.UserDto;
import com.example.FreemarkerShop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class HelloFreemarker {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/main")
    public String main() {
        return "main";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping(value = "/registration")
    public void registration2(UserDto userDto, HttpServletResponse response) throws IOException {
        log.info(userDto.getEmail());
        log.info(userDto.getName());
        userService.registration(userDto);
        response.sendRedirect("/login");
    }

    @GetMapping("/getImage")
    public void titleCrawling() {



    }


}
