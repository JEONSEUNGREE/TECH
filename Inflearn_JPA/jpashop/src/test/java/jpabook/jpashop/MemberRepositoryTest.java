//package jpabook.jpashop;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.Assert.*;
//
//// 스프링부트 테스트
//// junit에 스프링부트로 테스트함을 알려줌
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    public void testMember() throws Exception {
//
//        //given
//        Member member = new Member();
//
//        //when
//        member.setUsername("memberA");
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        //then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member);
//
//        System.out.println(findMember == member);
//
//    }
//}