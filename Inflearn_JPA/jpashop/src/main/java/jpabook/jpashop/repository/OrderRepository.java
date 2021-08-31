package jpabook.jpashop.repository;

//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.BooleanOperation;
//import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
//import jpabook.jpashop.domain.QMember;
//import jpabook.jpashop.domain.QOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public List<Order> findAllWithItem() {
        return em.createQuery(
//                distinct로 db에서는 중복제거가 안되지만 jpa에서 중복 pk에 해당하는 값을 제거해줌
//                단 다음쿼리는 페이징이 불가하다.
//                .setFirstResult(1)
//                .setMaxResults(100)
//                만약 위와같이 설정시에는 list offset과같은 쿼리가 날아가지않고 하이버네이트 경고가 발생하는데 모든 정보를 가져와서
//                메모리에올린다음에 페이징처리를해버린다. 이유 1대 다 fetch 조인을 했기때문에
//                우리기준에서는 order인데 db입장에서는 뻥튀기된 items의 값으로 조회를한다. 이부분에서 페이징을하면 orderItems 기준으로 페이징이되버린다.
//                그럼 fetch조인을 안쓰면 lazy를 적용해서 N + 1 문제를 어떻게 해결해야하는가.
//                일단 ToOne 관계는 fetch조인해도 상관없다 어차피 row수가 늘지않기때문에
                "select distinct o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item i", Order.class
        ).setFirstResult(1)
         .setMaxResults(100)
         .getResultList();
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
//        Order와 연관된 멤버 조인 동적쿼리(파라미터 주는쿼리) 는 queryDSL로 권장..
//         아래코드는 인제 파라미터값이 다 있다는 가정하에 작성된것
        List<Order> query = em.createQuery("select o from Order o join o.member m" +
                        "where o.status = :status " + "and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
//                결과를 최대치 지정 가능
//                .setFirstResult() 처음부터 몇개~
//                .setMaxResults(1000) 최대 몇개~
                .getResultList();
        return query;
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        //language=JPAQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }
//
//    public List<Order> findAllQueryDsl(OrderSearch orderSearch) {
//        QOrder order = QOrder.order;
//        QMember member = QMember.member;
//
//        JPAQueryFactory query = new JPAQueryFactory(em);
//
//
//        List<Order> result = query.select(order)
//                .from(order)
//                .join(order.member, member)
////                and조건 , 앞 ,(and) 뒤
//                .where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
//                .limit(1000)
//                .fetch();
//
//        return result;
//    }
//
//    private BooleanExpression nameLike(String memberName) {
//        if (!StringUtils.hasText(memberName)) {
//            return null;
//        }
//        return QMember.member.name.like(memberName);
//    }
//
//    private BooleanExpression statusEq(OrderStatus statusCond) {
////        주의 BooleanExpression 쿼리dsl ipmort
//        if (statusCond == null) {
//            return null;
//        }
//        return QOrder.order.status.eq(statusCond);
//    }

//  재사용성은 v4보다 좋음 엔티티로 조회해서 성능은 낮음
    public List<Order> findAllWithMemberDelivery() {
//        fetch 조인으로 가져오기
        return em.createQuery("select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order.class)
                .getResultList();
    }

//    정적쿼리기때문에 재활용성이 없다. 성능은 조금 더 좋다.
    public List<SimpleOrderQueryDto> findOrderDtos() {
//        아래 클래스가 dto로 들어간다
//        기본적으로 entity나 vo만 반환할수있다.또는 embaded
//        만약 아래와같이 쓰려면 new를 써줘야한다.
//        new jpabook.jpashop.repository.SimpleOrderQueryDto(o) 생성자에 o를 넣어버리면 식별자로 판단해서 안된다.
//        객체를 넘기면 식별자로 판단해버린다. 그래서 o.id 로 해야한다. address는 vo라 값처럼 동작함
//        어떻게보면 view에 의존적이라고 볼수있다 그래서 유지보수가 번거롭다.
        return em.createQuery("select new jpabook.jpashop.repository.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                                "from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class)
                .getResultList();

    }

    public List<Order> findAllWithMemberDelivery(int offset, int limit) {

        return em.createQuery("select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
