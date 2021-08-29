package jpabook.jpashop.api;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderQueryDto;
import jpabook.jpashop.repository.order.simpleQuery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/*
 *XToOne (ManyToOne, OneToOne)
 * order
 * order -> member (ManyToOne)
 * order -> delivery (OneToOne)
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
//        주문정보 전부 아래와같이 코드를 설정하면 스택이 터지고 오류가 나타는데
//        이는 order 엔티티에 들어가면 member가 있고 member안에는 오더가있어서 무한루프에 빠지게된다.
//        그리고 jpql의 경우 그대로 sql 번연을 통해 값을 가져왔는데 eager면 또 db에서 정보를 가져와서 N+1 발생
        List<Order> allByString = orderRepository.findAllByString(new OrderSearch());

//        for (Order order : allByString) {
//            order.getMember().getName(); //강제 lazy 초기화로 프록시정보를 실값으로 가져오도록 쿼리를 발생시킴
//            order.getDelivery().getAddress();
//        }
        return allByString;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
//            예상은 3테이블 3번조회 하지만 5번조회
//            ORDER-> SQL 1번 ->
//             만약 오더가 10개면 21방
//             N + 1 -> 1 + 회원 N + 배송 N
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
//          Lazy기때문에 아래 스트림맵으로 각기 하나씩 리스트로 저장할때마다 N+1로 가져옴
        List<SimpleOrderDto> result = orders.stream()
                .map(m -> new SimpleOrderDto(m))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

//    @GetMapping("/api/v4/simple-orders")
//    public List<SimpleOrderQueryDto> ordersV4() {
//        List<SimpleOrderQueryDto> result = orderRepository.findOrderDtos();
//        return result;
//    }

//    repository는 순수 entity 조회로쓰고 직접 딱 맞게 짠 쿼리는 따로 둔다.
    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4() {
        List<SimpleOrderQueryDto> result = orderSimpleQueryRepository.findOrderDtos();
        return result;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        //        entity를 받아도 상관없다 중요하지않은 의존성이기때문에
        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //Lazy초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();  //Lazy초기화

        }
    }
}
