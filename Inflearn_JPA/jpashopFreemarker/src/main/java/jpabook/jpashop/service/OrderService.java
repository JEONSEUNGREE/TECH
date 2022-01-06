package jpabook.jpashop.service;


import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class OrderService {

    private final EntityManager em;

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //    주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

//        엔티티조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

//        배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

//      주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
    
//        아래와같이 생성하는 생성자 막기 위에방식으로 생성하기로 정해서 꼬일수있다.
//        OrderItem orderItem1 = new OrderItem();   

//        주문상품 생성
        Order order = Order.createdOrder(member, delivery, orderItem);

//        주문 저장
//        cascadeType.ALl이기때문에 한군데 저장하면 자동으로 persist됨
//        cascade옵션을 주는 경우는 다른 곳에서 참조하는지 안하는지 여부로 갈린다.
//        다른곳에서 참조를한다면 쓰면 당연히 꼬이기때문에 좀더 상위 객체가 갖고 있는 부분에 주면 된다.
        orderRepository.save(order);

        return order.getId();
    }

    //    주문 쉬소
    @Transactional
    public void cancelOrder(Long orderId) {
//        주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
//        주문 취소
//        다음과같이 설정하면 jpa가 알아서 변경을 감지해서 update쿼리를 날려주고 직접 db에서 꺼내서 다시 작성할 수고가 덜어진다.
        order.cancel();
    }

//    검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
