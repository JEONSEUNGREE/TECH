package jpabook.jpashop.domain;


import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    //    아래 생성한 createOrderItem 생성자 외 에 생성자는 막기
//    혹은 @NoArgsConstructor(access = AccessLevel.PROTECTED)이방법법//    protected OrderItem() {
//    }

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //    생성메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }


//    비즈니스로직
//            재고수량 원복

    public void cancel() {
        getItem().addStock(count);
    }
//  전체상품 가격 조회 로직
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
