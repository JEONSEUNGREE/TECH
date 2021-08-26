//package hellojpa.hellojpa;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Repository
//@Transactional
//public class PostCustomRepositoryImpl implements PostCustomRespository {
//
//    @Autowired
//    EntityManager entityManager;
//
//    @Override
//    public List<Post> findMyPost() {
//        System.out.println("custom findMyPost");
//        return entityManager.createQuery("SELECT p FROM Post As p").getResultList();
//    }
//}
