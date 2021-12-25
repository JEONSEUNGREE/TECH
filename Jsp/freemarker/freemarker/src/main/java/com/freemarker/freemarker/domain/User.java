package com.freemarker.freemarker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    private int id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String userRole;
    private Timestamp createDate;
}
