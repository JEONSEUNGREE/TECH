package com.example.SpringBatch;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Data
public class Address {

    @Id @GeneratedValue
    private Long id;
    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
