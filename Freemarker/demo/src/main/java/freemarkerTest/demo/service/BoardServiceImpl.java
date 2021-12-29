package freemarkerTest.demo.service;

import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository repository;

    @Autowired
    EntityManager em;

    @Override
    public void post(Board board) {
        repository.save(board);
    }

    @Override
    public List<Board> boardList() {
        return repository.findAll();
    }

    @Override
    public Board boardRead(Long id) {
        return em.find(Board.class, id);
    }

    @Override
    public void boardDelete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void boardModify(Long id, String title, String contents) {
        Board board = em.find(Board.class, id);
        board.editBoard(title, contents);
    }
}