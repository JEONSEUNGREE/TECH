package com.example.FreemarkerShop.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter
public class Role {

    @Id @GeneratedValue
    private Long role_id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Role(String name, User user) {
        this.name = name;
        this.user = user;
        user.roles.add(this);
    }


}
