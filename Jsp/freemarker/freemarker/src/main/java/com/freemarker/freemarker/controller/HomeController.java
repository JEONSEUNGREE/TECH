package com.freemarker.freemarker.controller;


import com.freemarker.freemarker.domain.Board;
import com.freemarker.freemarker.domain.BoardDto;
import com.freemarker.freemarker.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    BoardService service;

    @RequestMapping("/home/main")
    public String showMain() {
        return "home/main";
    }

    @GetMapping("/register")
    public String test(Model model) {

        model.addAttribute("test", " JSP 테스트용");

        return "home/register";
    }

    @GetMapping("/boardList")
    public String boardList(Model model) {
        List<Board> boards = service.boardList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<BoardDto> boardDtoList = boards.stream().map(board -> new BoardDto(board.getId(), board.getTitle(),
                dateFormat.format(board.getRegDate()))).collect(Collectors.toList());

        model.addAttribute("boardList", boardDtoList);

        return "home/boardList";
    }


    @GetMapping("/post")
    public String post(Model model) {
        return "home/post";
    }

    @PostMapping("/writeAction")
    public String writeSuccess(Board board) {
        service.register(board.getTitle(), board.getWriter(), board.getContent());
        return "home/register";
    }

    @GetMapping("/boardReadPage/{id}")
    public String post(@PathVariable("id")Long id, Model model) {
        Board board = service.read(id);

        model.addAttribute("board", board);
        return "home/readPage";
    }

    @GetMapping("/boardReadPage/delete/{id}")
    public String delete(@PathVariable("id")Long id) {
        System.out.println("id : " + id);
        service.delete(id);
        return "home/register";
    }
}
