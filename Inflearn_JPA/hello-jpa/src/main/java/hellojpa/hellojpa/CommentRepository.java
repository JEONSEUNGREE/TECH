package hellojpa.hellojpa;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentContainsIgnoreCaseAndLikeCountGreaterThan(String keyword, int likeCount);

    Page<Comment> findByCommentContainsIgnoreCase(String keyword, Pageable pageable);

//    @Query("SELECT c FROM Comment AS c")
//    List<Comment> findByTitleContains(String keyword);

}
