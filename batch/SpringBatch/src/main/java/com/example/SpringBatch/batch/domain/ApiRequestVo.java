package com.example.SpringBatch.batch.domain;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiRequestVo {

    private long id;
    private ProductVO productVO;

}
