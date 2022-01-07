package com.example.FreemarkerShop.controller;


import com.example.FreemarkerShop.service.UserDto;
import com.example.FreemarkerShop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/products")
    public String productsPage(Model model) {

        //        헤더설정
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity entity = new HttpEntity(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();


        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:5000/imageCrawl", HttpMethod.GET, entity, String.class);
            System.out.println(responseEntity.getStatusCode());
            System.out.println(responseEntity.getBody());
            model.addAttribute("title", responseEntity.getBody());
        } catch (Exception e) {
            System.out.println(e);
        }


        return "products";
    }


}
