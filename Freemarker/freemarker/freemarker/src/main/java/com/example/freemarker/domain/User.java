package com.example.freemarker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long userId;

    private String name;

//    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Role> roles = new ArrayList<>();

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}
