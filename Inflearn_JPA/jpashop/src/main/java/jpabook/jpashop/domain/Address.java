package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
// 값타입은 값이 변경되지않게 해야된다. setter를 불가능하게 생성자만


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
