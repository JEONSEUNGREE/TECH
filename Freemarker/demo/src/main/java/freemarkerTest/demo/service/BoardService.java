package freemarkerTest.demo.service;

import freemarkerTest.demo.domain.Board;

import java.util.List;

public interface BoardService {

    void post(Board board);

    List<Board> boardList();

    Board boardRead(Long id);

    void boardDelete(Long id);

    void boardModify(Long id, String title, String contents);
}
