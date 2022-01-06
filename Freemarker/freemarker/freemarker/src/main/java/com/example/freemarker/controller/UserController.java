package com.example.freemarker.controller;


import com.example.freemarker.domain.UserDto;
import com.example.freemarker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping(value = "/registration")
    public String registration2(UserDto userDto) {
        log.info(userDto.getEmail());
        log.info(userDto.getName());
        userService.registration(userDto);
        return "index";
    }

    @GetMapping(value = "/index")
    public String main() {
        return "/index";
    }

}
