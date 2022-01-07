package freemarkerTest.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String regDate;

    public BoardDto(Long id, String title, String date) {
        this.id = id;
        this.title = title;
        this.regDate = date;
    }
}
