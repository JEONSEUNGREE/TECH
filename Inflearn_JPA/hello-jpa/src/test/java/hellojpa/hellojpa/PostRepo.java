//package hellojpa.hellojpa;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class PostRepo {
//    //
//    @Autowired
//    PostRepository postRepository;
//    //
//    @Test
//    public void crudRepository() {
//        Post post = new Post();
//
//        post.setTitle("hello");
//        assertThat(post.getId()).isNotNull();
//
//        Post newPost = postRepository.save(post);
//
//        assertThat(newPost.getId()).isNotNull();
//
//        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
//        assertThat(page.getTotalElements()).isEqualTo(1);
//        assertThat(page.getNumber()).isEqualTo(0);
//        assertThat(page.getSize()).isEqualTo(10);
//
//    }
//
//}