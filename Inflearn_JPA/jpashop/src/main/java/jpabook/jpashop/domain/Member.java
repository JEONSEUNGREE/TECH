package jpabook.jpashop.domain;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

//    order테이블에있는 멤버필드에의해 매핑됬음을 알림
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
