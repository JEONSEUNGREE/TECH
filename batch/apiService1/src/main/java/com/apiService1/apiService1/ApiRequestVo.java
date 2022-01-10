package com.apiService1.apiService1;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiRequestVo {

    private long id;
    private ProductVO productVo;
}
