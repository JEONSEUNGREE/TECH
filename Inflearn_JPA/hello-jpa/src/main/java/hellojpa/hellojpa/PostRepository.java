package hellojpa.hellojpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

//@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByTitleContains(String title, Pageable pageable);

//// jpa코드만 사용해서 빈주입 최대한 spring코드를 적게 노출하는 목적으로 작성시의 예
//    @PersistenceContext
//    EntityManager entityManager;
//
//    public Post add(Post post) {
//        entityManager.persist(post);
//        return post;
//    }
//
//    public void delete(Post post) {
//        entityManager.remove(post);
//    }
//
//    public List<Post> findAll() {
//        return entityManager.createQuery("SELECT p FROM Post As p", Post.class)
//                .getResultList();
//    }


}
