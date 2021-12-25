package com.freemarker.freemarker.repository;

import com.freemarker.freemarker.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.id = :id")
    Board getBoardInfo(@Param("id") Long id);
}
