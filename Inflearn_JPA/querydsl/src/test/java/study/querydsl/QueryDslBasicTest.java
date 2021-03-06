package study.querydsl;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.*;
import static study.querydsl.entity.QTeam.*;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;
//  동시성문제 X 필드에서 사용가능함
//        엔티티매니저를 쿼리팩토리에 넘겨준다. -> 쿼리팩토리가 데이터를 찾는다.
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void stratJPQL() {
        //member1을 찾아라.
        Member findByMember = em.createQuery(
                "select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findByMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQueryDsl() {

//        변수명에 별칭을 적용
//        QMember m = new QMember("m");

//      일반 쿼리문이랑 매우 유사하다.
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.between(10,30)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.between(10,30)))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch() {
//        List<Member> fetch = queryFactory
//                .selectFrom(member)
//                .fetch();
//
//        Member fetchOne = queryFactory
//                .selectFrom(member)
//                .fetchOne();
//
//        Member fetchFirst = queryFactory
//                .selectFrom(QMember.member)
////                limit(1).fetchOne과같음
//                .fetchFirst();
//
//        QueryResults<Member> results = queryFactory.
//                selectFrom(member)
//                .fetchResults();
//
////      토탈카운트땜에 쿼리 두방
//        results.getTotal();
//
//        List<Member> content = results.getResults();

        long total = queryFactory
                .selectFrom(member)
                .fetchCount();
    }

    /*
    * 회원 나이 내림차순
    * 회원 이름 올림차순
    * 회원이름이 없으면 마지막에 nulls last 출력
    * */
    @Test
    public void sort() throws Exception {
        //given
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();
        //when

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");

        assertThat(memberNull.getUsername()).isNull();
        //then
    }

    @Test
    public void paging1() throws Exception {
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetch();
        //when

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2() throws Exception {
        //given
        QueryResults<Member> queryResults = queryFactory
                .selectFrom(member)
                .orderBy(member.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();
        //when

        //then
        assertThat(queryResults.getTotal()).isEqualTo(4);
        assertThat(queryResults.getLimit()).isEqualTo(2);
        assertThat(queryResults.getOffset()).isEqualTo(1);
        assertThat(queryResults.getResults()).size().isEqualTo(2);
    }

    @Test
    public void aggregation() {
//        튜플로찍어서 가져옴 - 쿼리 dsl튜플
//        튜플보다는 dto로 찍어서 가져오는 방법을 많이씀
        List<Tuple> result = queryFactory
                .select(
                        member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.max(),
                        member.age.min()
                ).
                from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }


    /*
    *팀의 이름과 각 팀의 평균 연령을 구해라.
    * */
    @Test
    public void group() throws Exception {
        List<Tuple> result = queryFactory
                .select(
                        team.name,
                        member.age.avg()
                )
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

//        groupBy team.name이라 두개
//        having도 가능
        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15); //(10 + 20) /2

        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35); //(30 + 40) /2
    }

//    teamA에 속한 모든 회원
    @Test
    public void join() {
//        innerjoin예
        List<Member> result = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

//    연관관계없는 join 세타조인
//    회원이름과 팀이름이 같은 회원조회
    @Test
    public void theta_join() throws Exception {
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        //when
//        모든회원과 팀을 가져와서 같은것을 찾아서 곱하는 조인
        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
        //then
        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

    }

//    회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
//    JPQL: select m from Member m left join m.team t on t.name = teamA
    @Test
    public void join_on_filetering() throws Exception {
        //given
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
//                .leftJoin(member.team, team).on(team.name.eq("teamA"))
                .fetch();
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

//    연관관계없는 엔티티 외부 조인
    @Test
    public void join_on_no_relation() throws Exception {
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        //when
//        모든회원과 팀을 가져와서 같은것을 찾아서 곱하는 조인
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team).on(member.username.eq(team.name))
                .fetch();
        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
        }

    @PersistenceUnit
    EntityManagerFactory emf;

        @Test
        public void fetchJoinNo() throws Exception {
            //given
            em.flush();
            em.clear();

            Member findMember = queryFactory
                    .selectFrom(QMember.member)
                    .join(member.team, team).fetchJoin()
                    .where(QMember.member.username.eq("member1"))
                    .fetchOne();

            boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
            assertThat(loaded).as("페치 조인 미적용").isTrue();
            //when
            
            //then
        }

//        나이가 가장 많은 회원 조회
//          서브쿼리
        @Test
        public void subQuery() throws Exception {
            QMember memberSub = new QMember("MemberSub");

            List<Member> result = queryFactory
                    .selectFrom(member)
                    .where(member.age.eq(
//                            서브쿼리는 별칭이 달라야해서 새로 Qmember설정
                            select(memberSub.age.max())
                                    .from(memberSub)
                    ))
                    .fetch();

            assertThat(result).extracting("age").containsExactly(40);
        }

        //        나이가 평균이상인 회원 조회
        //          서브쿼리 goe
        @Test
        public void subQueryGoe() throws Exception {

            QMember memberSub = new QMember("MemberSub");

            List<Member> result = queryFactory
                    .selectFrom(member)
                    .where(member.age.goe(
//                            서브쿼리는 별칭이 달라야해서 새로 Qmember설정
                            select(memberSub.age.avg())
                                    .from(memberSub)
                    ))
                    .fetch();

            assertThat(result).extracting("age").containsExactly(30,40);
        }

        //        나이가 포함되있는는
       //          서브쿼리 in
        @Test
        public void subQueryIn() throws Exception {

            QMember memberSub = new QMember("MemberSub");

            List<Member> result = queryFactory
                    .selectFrom(member)
                    .where(member.age.in(
                            select(memberSub.age)
                                    .from(memberSub)
                                    .where(memberSub.age.gt(10))

                    ))
                    .fetch();

            assertThat(result).extracting("age").containsExactly(20,30,40);
        }

//        select 서브쿼리 from절은 사용불가 웬만하면 join으로 해결하고 안되면 nativeSQL
    @Test
    public void selectSubQuery() {
        QMember memberSub = new QMember("memberSub");
        List<Tuple> result = queryFactory.
                select(member.username,
                        select(memberSub.age.avg())
                                .from(memberSub))
                .from(member)
                .fetch();

        for (Tuple tuple: result) {
            System.out.println("tuple = " + tuple);

        }
    }
//  case문 웬만하면 view쪽이나 로직으로 해결하는것으로 권장됨

    @Test
    public void basicCase() throws Exception {
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
    @Test
    public void complexCase() throws Exception {
        List<String> result
                = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20")
                        .when(member.age.between(21, 30)).then("21~30")
                        .otherwise("기타"))
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
//  concat stringValue() 부분은 문자가아닌다른타입들을 처리해준다.(Enum등)
    @Test
    public void contcat() throws Exception {

            //username_age
        List<String> result = queryFactory.select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);

        }


    }

//    프로젝션 하나인경우 셀렉, 여러개 튜플 또는 DTO

    @Test
    public void simpleProjection() throws Exception {

        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = " + s);
        }

    }

//    튜플은 쿼리DSL것이다. jdbc를 쓸떄도 레포지토리 안에서나 dao쪽에서만 사용하고 나머지 계층에는 의존이 안생겨서 수정시 편하도록 하는게 좋은 설계였다.
//    마찬가지로 튜플도 레포지토리안에서만 쓸수있도록하고 나갈때는 dto로 바꿔야한다.
    @Test
    public void tupleProjection() throws Exception {
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);
            System.out.println("username = " + username);
            System.out.println("age = " + age);

        }
    }

//    dto 프로젝션
//    일반 new operation
    @Test
    public void findDto() throws Exception {
//            jpql
        List<MemberDto> result = em.createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);

        }
    }

//    querydsl 프로젝션 1.프로퍼티 getter,setter,생성자필요

    @Test
    public void findDtoBySetter() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.bean(
                        MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }
//      2. field로 조회 getter,setter필요없음
    @Test
    public void findDtoByFields() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.fields(
                        MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }
    //      3. 생성자로 조회 필드명 변수가 일치해야됨
    @Test
    public void findDtoByConstructor() throws Exception {
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(
                        MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

//    이름이 달라서 안됨 하지만 as로 바꾸면 가능

    @Test
    public void findByUserDto() throws Exception {
        List<UserDto> result = queryFactory
                .select(Projections.fields(
                        UserDto.class,
                        member.username.as("name"),
                        member.age))
                .from(member)
                .fetch();

        for (UserDto memberDto : result) {
            System.out.println("useDto = " + memberDto);
        }
    }
//    다음과같인 서브쿼리로 별칭을써서 조회가능
//    List<UserDto> fetch = queryFactory
//            .select(Projections.fields(UserDto.class,
//                            member.username.as("name"),
//                            ExpressionUtils.as(
//                                    JPAExpressions
//                                            .select(memberSub.age.max())
//                                            .from(memberSub), "age")
//                    )
//            ).from(member)
//            .fetch();

//    장점 @QueryProjection은 컴파일오류가 잡히고 constructor는 런타임오류가남
//    단점 QueryDsl에 의존적
    @Test
    public void fidDtoByQueryProjection() throws Exception {
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();

        for (MemberDto memberDto : result) {
            System.out.println("result = " + memberDto);

        }
    }

//    BooleanBuilder를 이용한 동적 쿼리작성
//    예전 String query에 비하면 가독성,코드 효율상승
    @Test
    public void dynamicQuery_BooleanBuilder() throws Exception {
        String usernameParm = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParm, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }


    private List<Member> searchMember1(String usernameCond, Integer ageCond) {

        BooleanBuilder builder = new BooleanBuilder();
        if (usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }
        if (ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();
    }

//    Booleanbuild보다 깔끔함
    @Test
    public void dynamicQuery_WhereParam() throws Exception {
        String usernameParm = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParm, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
//                .where(usernameEq(usernameCond), ageEq(ageCond))

//                아래는 한번더 감싼것 위는 나눈것것
               .where(allEq(usernameCond,ageCond))
                .fetch();
    }
//  조립하려면 Predicate 말고 BooleanExpression
    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond == null ? member.age.eq(ageCond) : null;
    }

    private BooleanExpression usernameEq(String usernameCond) {
        if (usernameCond == null) {
            return null;
        }
        return member.username.eq(usernameCond);
    }

    private BooleanExpression allEq(String useranmeCond, Integer ageCond) {
        return usernameEq(useranmeCond).and(ageEq(ageCond));
    }

//    벌크 단점 바로 commit되버리기때문에 1차캐시 남아있는 값과 db와 차이가 있다.
//    다시 불러온다한들 중복이있으면 영속성컨텍스트를 우선시해서 db에서 가져온것을 버림.
//    벌크는 쓰고 뒤에 변경로직을 최대한 추가하지 않도록 하거나 한번 flush,clear로 동기,초기화를 시켜준다.
    @Test
    public void bulkUpdate() {

//            영향을 받을 row수가 나옴
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
//                lt = 미만
                .where(member.age.lt(28))
                .execute();

        assertThat(count).isEqualTo(2);
    }
//  (-1)add도가능,multiply등등
    @Test
    public void bulkAdd() {
        queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
    }

    @Test
    public void bulkDelete() throws Exception {
        queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
    }
//  function은 db벤더마다 다르니깐 dialect에따라서 검색해서본다.
    @Test
    public void sqlFunction() throws Exception {
        List<String> result = queryFactory
                .select(
                        Expressions.stringTemplate
                                ("function('replace', {0}, {1}, {2})",
                                        member.username, "member", "M")
                ).from(member)
                .fetch();
        for (String s : result) {
            System.out.println("s = " + s);

        }
    }
//  아래와같이 쿼리Dsl도 제공하는게 있음
    @Test
    public void sqlFunction2() {
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
//                .where(member.username.eq(Expressions.stringTemplate("function('lower', {0}", member.username)))
                .where(member.username.eq(member.username.lower()))
                .fetch();

        for (String s : result) {
            System.out.println("result = " + s);

        }

    }


}
