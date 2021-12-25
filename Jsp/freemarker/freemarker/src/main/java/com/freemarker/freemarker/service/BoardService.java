package com.freemarker.freemarker.service;

import com.freemarker.freemarker.domain.Board;

import java.util.List;

public interface BoardService {

    void register(String title, String writer, String content);

    List<Board> boardList();

    void delete(Long id);

    Board read(Long id);

}
