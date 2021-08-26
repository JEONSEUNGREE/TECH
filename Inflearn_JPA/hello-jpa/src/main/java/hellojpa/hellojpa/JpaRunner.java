package hellojpa.hellojpa;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
//모든 오는 한 트랜잭션안에서 적용되어야한다.
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext
    EntityManager entityManager;
//    엔티티 매니저를 통해 영속성을 부여하기위한 작업준비

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Post post = new Post();
//
//        post.setTitle("Spring Data JPA 언제 보나.....");
//
//        Comment comment = new Comment();
//        comment.setComment("제발보고싶다.");
//        post.addComment(comment);
//
//        Comment comment1 = new Comment();
//
//        comment1.setComment("곧보여드릴게요");
//        post.addComment(comment1);
//        Session session = entityManager.unwrap(Session.class);
//
//        session.save(post);
//
//        session.flush();
//
//        postRepository.findAll().forEach(System.out::println);
//
//        List<Post> posts = entityManager.createNativeQuery("select * from post", Post.class)
//                .getResultList();
//
//        posts.forEach(System.out::println);

//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Post> query = builder.createQuery(Post.class);
//
//        Root<Post> root = query.from(Post.class);
//        query.select(root);
//
//        List<Post> posts = entityManager.createQuery(query).getResultList();
//
//        posts.forEach(System.out::println);

//        TypedQuery<Post> query =  entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
//        List<Post> posts = query.getResultList();
//
//        posts.forEach(System.out::println);
//        session.clear();
//        Post post1 = session.get(Post.class, 1L);
//        System.out.println(post1.getTitle());
//
////        Comment comment2 = session.get(Comment.class, 2L);
//
//        post.getComments().forEach(c -> {
//            System.out.println(c.getComment());
//        });
//
//        Account account = new Account();
//        account.setUsername("haha");
//        account.setPassword("jpa");
//
//        Study study = new Study();
//        study.setName("Spring Data JPA");
////        study.setOwner(account);
//
//        account.getStudies().add(study);
//
//        account.addStudy(study);
//
//        account.remove(study);
//
//        Session session = entityManager.unwrap(Session.class);
//
//        session.save(account);
//        session.save(study);
    }
}
