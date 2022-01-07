package freemarkerTest.demo.service;

import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<Board> boardList();

    Board readBoard(Long id);

    void post(BoardDto boardDto);
}
