package com.example.SpringBatch;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;


@Data
@Entity
public class Address {

    @Id @GeneratedValue
    private Long id;

    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
