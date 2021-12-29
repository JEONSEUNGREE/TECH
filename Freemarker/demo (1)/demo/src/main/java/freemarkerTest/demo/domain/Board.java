package freemarkerTest.demo.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity @NoArgsConstructor @Data
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    @CreationTimestamp
    private Date regDate;

    @Builder
    public Board(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
