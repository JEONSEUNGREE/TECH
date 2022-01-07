package freemarkerTest.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long memberId;

    private String name;

    private String email;

    private String password;

    private Long phoneNumber;

    private String auth;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Orders> orders = new ArrayList<>();

}
