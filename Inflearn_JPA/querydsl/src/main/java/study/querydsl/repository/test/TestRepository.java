package study.querydsl.repository.test;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;

import javax.persistence.EntityManager;

import static study.querydsl.entity.QMember.*;

@Repository
public class TestRepository {

    private final JPAQueryFactory QueryFactory;

    public TestRepository(EntityManager em, JPAQueryFactory queryFactory) {
        this.QueryFactory = new JPAQueryFactory(em);
    }

    public String memberFind(Long Id) {

        return QueryFactory
                .select(member.username)
                .from(member)
                .where(member.id.eq(Id))
                .fetchOne();

    }
}
