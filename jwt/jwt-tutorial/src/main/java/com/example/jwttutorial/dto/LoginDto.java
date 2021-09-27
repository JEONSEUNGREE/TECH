package com.example.jwttutorial.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
//    validation라이브러리 관련 어노테이션
    @Size(min = 3, max = 100)
    private String password;
}
