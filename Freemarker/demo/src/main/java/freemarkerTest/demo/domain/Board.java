package freemarkerTest.demo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    private String writer;

    private Date regDate;

    public void editBoard(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void editDate(Date date) {
        this.regDate = date;
    }

    @Builder
    public Board(String title, String contents, String writer, Date regDate) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.regDate = regDate;
    }
}
