//package jpabook.jpashop;
//
//import jpabook.jpashop.domain.*;
//import jpabook.jpashop.domain.item.Book;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//
//
//
//@Component
//@RequiredArgsConstructor
//public class initDb {
//
//    private final InitService initService;
//
////    애플리케이션 로딩 시점에서 아래 코드 호출
//    @PostConstruct
//    public void init() {
//        initService.dbInit1();
//        initService.dbInit2();
//    }
//
//
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final EntityManager em;
//
//        public void dbInit1() {
//            Member member = createMember("userA", "수원", "동구밭", "2342");
//            em.persist(member);
//
//            Book book1 = createBook("JPA1 BOOK", 10000, 200);
//            em.persist(book1);
//
//
//            Book book2 = createBook("JPA2 BOOK", 20000, 300);
//            em.persist(book2);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 3);
////            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 4);
//
//            Delivery delivery = createDelivery(member);
//            Order order = Order.createdOrder(member, delivery, orderItem1);
//
//            em.persist(order);
//        }
//
//        public void dbInit2() {
//            Member member = createMember("userB", "서울", "어딘가", "1111");
//            em.persist(member);
//
//            Book book1 = createBook("JPA1 BOOK", 10000, 100);
//            em.persist(book1);
//
//
//            Book book2 = createBook("JPA2 BOOK", 20000, 100);
//            em.persist(book2);
//
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 5);
////            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 6);
//
//            Delivery delivery = createDelivery(member);
//            Order order = Order.createdOrder(member, delivery, orderItem1);
//
//            em.persist(order);
//        }
//        private Book createBook(String name, int price, int StockQuantity) {
//            Book book1 = new Book();
//            book1.setName(name);
//            book1.setPrice(price);
//            book1.setStockQuantity(StockQuantity);
//            return book1;
//        }
//
//        private Member createMember(String name, String city, String street, String zipcode) {
//            Member member = new Member();
//            member.setName(name);
//            member.setAddress(new Address(city, street, zipcode));
//            return member;
//        }
//
//        private Delivery createDelivery(Member member) {
//            Delivery delivery = new Delivery();
//            delivery.setAddress(member.getAddress());
//            delivery.setStatus(DeliveryStatus.READY);
//            return delivery;
//        }
//    }
//    }
//
//
//
//
//
//
