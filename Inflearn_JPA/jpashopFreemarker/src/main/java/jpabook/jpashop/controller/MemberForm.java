package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraint 에서 다양한 어노테이션이있음

@Getter @Setter
public class MemberForm {
//implementation 'org.springframework.boot:spring-boot-starter-validation' 어노테이션

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty
//    @Min(value = 5)
    private String city;

    private String street;
    private String zipcode;
}
