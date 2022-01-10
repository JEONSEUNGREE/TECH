package com.example.SpringBatch.batch.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    private Long id;

    private String name;
    private int price;
    private String type;

}
