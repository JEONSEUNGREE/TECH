package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orders")
public class Order {
// 객체는 order.id 이런식으로 찍으면 나오지만 테이블은 id로 통일하면 헷갈리기때문에 DBA분들및 기타 문제로 명시
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

//    oneToone의 경우 FK를 양쪽 어디에 둬도 상관없지만
//    최대한 액세스가 많고 주문이 배송을 찾지 배송에서 주문을 찾는경우는 적기때문
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "deliver_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    //[ORDER, CANCEL]


}
