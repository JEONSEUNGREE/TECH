package freemarkerTest.demo.service;

import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.dto.BoardDto;
import freemarkerTest.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Override
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Override
    public Board readBoard(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public void post(BoardDto boardDto) {
        boardRepository.save(Board.builder()
                .contents(boardDto.getContents())
                .title(boardDto.getTitle())
                .build());
    }

}
