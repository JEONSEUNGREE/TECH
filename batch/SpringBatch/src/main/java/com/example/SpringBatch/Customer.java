package com.example.SpringBatch;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
public class Customer {

    @Id @GeneratedValue
    private Long customer_id;

    private String username;

    private int age;

    @OneToOne(mappedBy = "customer")
    private Address address;



}
