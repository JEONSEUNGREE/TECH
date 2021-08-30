package jpabook.jpashop.api;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.query.OrderFlatDto;
import jpabook.jpashop.repository.order.query.OrderQueryDto;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();

            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrdersDto> orderVe2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrdersDto> result = orders.stream()
                .map(OrdersDto::new)
//                .map(o -> new OrdersDto(o)) 위와같은 식
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v3/orders")
    public List<OrdersDto> orderVe3() {
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrdersDto> result = orders.stream()
                .map(OrdersDto::new)
//                .map(o -> new OrdersDto(o)) 위와같은 식
                .collect(Collectors.toList());
        return result;
    }

//    v3의경우 쿼리는 하나지만 items만큼 데이터 row값이 뻥튀기되서 row가 증가한다 items가 여러개인경우 row가 그만큼 증가한다는 뜻이다.
//    v3.1의경우 쿼리는 3개지만 정확하게 테이블만 필요한 정보만 가져와서 DB  데이터 전송량이 감소.페이징도 가능
//     글로벌 설정: default_batch_fetch_size: 100 - 인쿼리로 설정
//    로컬 설정: 엔티티나 컬럼에 @BatchSize(size = 갯수)로 설정하면됨
    @GetMapping("/api/v3.1/orders")
    public List<OrdersDto> orderVe3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "offset", defaultValue = "100") int limit
            ) {
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrdersDto> result = orders.stream()
                .map(OrdersDto::new)
//                .map(o -> new OrdersDto(o)) 위와같은 식
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> orderVe4() {
        return orderQueryRepository.findOrderQueryDtos();


    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> ordersV6() {
        return orderQueryRepository.findAllByDto_flat();
    }


    @Data
    static class OrdersDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        //        이 OrderItem도 entity니깐 이부분도 수정해야한다. 이마저도 view의존적으로 변경될수있기때문
        private List<OrderItemDto> orderItems;

        public OrdersDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getDelivery().getOrder().getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
//            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
//            orderItems = order.getOrderItems();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }

        @Data
        static class OrderItemDto {

            private String name;
            private int orderPrice;
            private int count;

            public OrderItemDto(OrderItem orderItem) {
                name = orderItem.getItem().getName();
                orderPrice = orderItem.getOrderPrice();
                count = orderItem.getCount();
            }
        }
    }


}
