package com.example.SpringBatch;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @Id @GeneratedValue
    private Long customer_id;

    private String username;

    private int age;

    @OneToOne(mappedBy = "customer")
    private Address address;


}
