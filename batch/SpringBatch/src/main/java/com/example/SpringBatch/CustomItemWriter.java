package com.example.SpringBatch;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class CustomItemWriter implements ItemWriter<Customer> {


    @Override
    public void write(List<? extends Customer> items) throws Exception {
        items.forEach(i -> System.out.println(i));
    }
}
