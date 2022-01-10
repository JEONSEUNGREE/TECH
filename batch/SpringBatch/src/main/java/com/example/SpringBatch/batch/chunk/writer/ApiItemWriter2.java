package com.example.SpringBatch.batch.chunk.writer;

import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.service.AbstractApiService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import java.util.List;



public class ApiItemWriter2 implements ItemWriter<ApiRequestVo> {

    private final AbstractApiService apiService;

    public ApiItemWriter2(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List items) throws Exception {

    }
}
