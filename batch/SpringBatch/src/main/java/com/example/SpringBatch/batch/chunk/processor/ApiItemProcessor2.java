package com.example.SpringBatch.batch.chunk.processor;

import com.example.SpringBatch.batch.domain.ApiRequestVo;
import com.example.SpringBatch.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;

public class ApiItemProcessor2 implements ItemProcessor<ProductVO, ApiRequestVo> {


    @Override
    public ApiRequestVo process(ProductVO item) throws Exception {
        return ApiRequestVo.builder()
                .id(item.getId())
                .productVO(item)
                .build();
    }
}
