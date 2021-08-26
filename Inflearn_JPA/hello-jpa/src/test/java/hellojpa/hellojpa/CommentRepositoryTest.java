package hellojpa.hellojpa;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @After
    public void cleanUp() {
        commentRepository.deleteAll();
    }

    @Test
    public void crud(){
        Comment comment = new Comment();
        comment.setComment("SpringJpa");
        comment.setLikeCount(100);

        Comment comment1 = new Comment();
        comment1.setComment("springdatajpa");
        comment1.setLikeCount(55);
        commentRepository.save(comment1);
        commentRepository.save(comment);

        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "likeCount"));

        List<Comment> comments = commentRepository.findByCommentContainsIgnoreCaseAndLikeCountGreaterThan("springJpa", 10);

        Page<Comment> comments1 = commentRepository.findByCommentContainsIgnoreCase("springJpa", pageRequest);

        assertThat(comments1.getNumberOfElements()).isEqualTo(2);

        assertThat(comments.size()).isEqualTo(1);
    }


}