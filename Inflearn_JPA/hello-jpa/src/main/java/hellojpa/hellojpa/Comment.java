package hellojpa.hellojpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private Integer likeCount = 0;
//
//    @ManyToOne
//    private Post post;

}
