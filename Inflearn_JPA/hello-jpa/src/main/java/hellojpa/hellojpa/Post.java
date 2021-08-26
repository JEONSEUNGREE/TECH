package hellojpa.hellojpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    private Long Id;

    private String title;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Post publish() {
        this.registerEvent(new PostPublishedEvent(this));

        return this;
    }
//    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
//    private Set<Comment> comments = new HashSet<>();

//    public void addComment(Comment comment) {
//        this.getComments().add(comment);
//        comment.setPost(this);
//    }


}
