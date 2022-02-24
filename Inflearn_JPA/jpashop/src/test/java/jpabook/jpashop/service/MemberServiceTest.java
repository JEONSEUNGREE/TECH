package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.MemberRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //중복예외발생 안전하게 실수로 테스트코드를 잘못작성했을때를 방지하기 위해서 try-catch문을 작성한다.
//        혹은 @org.junit.Test(expected = IllegalStateException.class)만 작성해도 된다. try-catch 필요없음
        } catch (IllegalStateException e) {
            return;
        }
//        예외발생시 예외를 catch하지 못하면 테스트가 정상적이지 못하다. 따라서 예외를 잡을수있도록 설정

        //then
//        예외가 발생해야함에도 아래의 코드가 출력되면 잘못된부분
        fail("예외가 발생해야 한다.");

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void osivTest () {
        //given

        Member member = new Member("TestOSIV", new Address("testLocation1", "testLocation2", "testLocation3"));
        em.persist(member);

        Book book2 = createBook("JPA2 BOOK", 20000, 100);
        em.persist(book2);

        OrderItem orderItem = OrderItem.createOrderItem(book2, 20000, 4);
        Delivery delivery = createDelivery(member);
        Order order = Order.createdOrder(member, delivery, orderItem);
        em.persist(order);

        //when


        //then

    }


    private Book createBook(String name, int price, int StockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(StockQuantity);
            return book1;
        }
            private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery.setStatus(DeliveryStatus.READY);
            return delivery;
        }

}