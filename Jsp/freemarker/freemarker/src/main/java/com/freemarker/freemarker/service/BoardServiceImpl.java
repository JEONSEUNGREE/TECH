package com.freemarker.freemarker.service;

import com.freemarker.freemarker.domain.Board;
import com.freemarker.freemarker.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public void register(String title, String writer, String content) {

        Board board = Board.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .regDate(new Date())
                .build();

        repository.save(board);

    }

    @Override
    public List<Board> boardList() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Board read(Long id) {
        return repository.getBoardInfo(id);
    }
}
