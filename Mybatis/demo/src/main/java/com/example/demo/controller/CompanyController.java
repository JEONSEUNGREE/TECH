package com.example.demo.controller;

import com.example.demo.repository.CompanyMapper;
import com.example.demo.vo.CompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("/")
    public String test() {
        return "test";
    }


    @GetMapping("/company")
    public int post(@RequestBody CompanyVo companyVo) {
        log.info(companyVo.getCompanyAddress());
        return companyMapper.insert(companyVo);
    }

}
