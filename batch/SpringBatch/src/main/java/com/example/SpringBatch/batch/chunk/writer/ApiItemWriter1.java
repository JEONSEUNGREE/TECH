package com.example.SpringBatch.batch.chunk.writer;

import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.domain.ApiResponseVO;
import com.example.SpringBatch.batch.service.AbstractApiService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ApiItemWriter1 implements ItemWriter<ApiRequestVo> {

    private final AbstractApiService apiService;

    public ApiItemWriter1(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(List items) throws Exception {
        ApiResponseVO responseVO = apiService.service(items);
        System.out.println("responseVo = " + responseVO);

    }
}
