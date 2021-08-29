package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
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
}
