package jpabook.jpashop.repository;

import com.querydsl.core.annotations.QueryProjection;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        
        if (item.getId() == null) {
//            아이템이 없으면 그대로 저장
            em.persist(item);
        } else {
//            있다면 merge 업데이트같은 느낌
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
