package com.example.freemarker.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor
public class Role {

    @Id @GeneratedValue
    private Long roleId;

    private String name;

    public Role(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user = new User();

    public void addUser(User user) {
        this.user = user;
        user.addRole(this);
    }


}
