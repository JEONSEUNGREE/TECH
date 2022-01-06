package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberRepositoryNew extends JpaRepository<Member, Long> {

//  select m from Member m where m.name = ?
    List<Member> findByName(String name);

}
