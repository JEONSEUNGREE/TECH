package com.apiService1.apiService1;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ApiController {

//     각 서버라고 가정하기위해 /1,/2/,/3 으로 URI 맵핑
    @PostMapping(value = "/api/product/1")
    public String product1(@RequestBody ApiInfo apiInfo) {

        List<ProductVO> collect = apiInfo.getApiRequestList().stream().map(item -> item.getProductVo()).collect(Collectors.toList());
        System.out.println("collect = "  + collect);

        return "product1 was successfully processed";
    }

    @PostMapping(value = "/api/product/2")
    public String product2(@RequestBody ApiInfo apiInfo) {

        List<ProductVO> collect = apiInfo.getApiRequestList().stream().map(item -> item.getProductVo()).collect(Collectors.toList());
        System.out.println("collect = "  + collect);

        return "product2 was successfully processed";
    }

    @PostMapping(value = "/api/product/3")
    public String product3(@RequestBody ApiInfo apiInfo) {

        List<ProductVO> collect = apiInfo.getApiRequestList().stream().map(item -> item.getProductVo()).collect(Collectors.toList());
        System.out.println("collect = "  + collect);

        return "product3 was successfully processed";
    }

}
