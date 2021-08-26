package hellojpa.hellojpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest 데이터관련빈만 가져와서 슬라이싱으로 테스트함
@Import(PostRepositoryTestConfig.class)
public class PostRepositoryTest {
//
    @Autowired
    PostRepository postRepository;

    @Autowired
    ApplicationContext applicationContext;
//

    @Test
    public void event() {
        Post post = new Post();
        post.setTitle("event");

        postRepository.save(post.publish());

        PostPublishedEvent event = new PostPublishedEvent(post);

        applicationContext.publishEvent(event);
    }
    @Test
    public void crudRepository() {
        System.out.println("출력");
//        postRepository.findMyPost();
//        Post post = new Post();
//
//        post.setTitle("hello");
////        assertThat(post.getId()).isNotNull();
//
//        Post newPost = postRepository.save(post);
//
//        assertThat(newPost.getId()).isNotNull();

    }

}