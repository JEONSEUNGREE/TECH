package com.freemarker.freemarker.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private String writer;

    private String content;

    private Date regDate;

    @Builder
    public Board(String title, String writer, String content, Date regDate) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.regDate = regDate;
    }
}
