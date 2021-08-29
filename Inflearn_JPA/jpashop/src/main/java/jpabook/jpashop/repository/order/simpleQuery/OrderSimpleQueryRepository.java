package jpabook.jpashop.repository.order.simpleQuery;


import jpabook.jpashop.repository.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    //    정적쿼리기때문에 재활용성이 없다. 성능은 조금 더 좋다.
    public List<SimpleOrderQueryDto> findOrderDtos() {
//        아래 클래스가 dto로 들어간다
//        기본적으로 entity나 vo만 반환할수있다.또는 embaded
//        만약 아래와같이 쓰려면 new를 써줘야한다.
//        new jpabook.jpashop.repository.SimpleOrderQueryDto(o) 생성자에 o를 넣어버리면 식별자로 판단해서 안된다.
//        객체를 넘기면 식별자로 판단해버린다. 그래서 o.id 로 해야한다. address는 vo라 값처럼 동작함
//        어떻게보면 view에 의존적이라고 볼수있다 그래서 유지보수가 번거롭다.
        return em.createQuery("select new jpabook.jpashop.repository.order.simpleQuery.SimpleOrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class)
                .getResultList();

    }
}
