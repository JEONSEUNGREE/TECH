package freemarkerTest.demo.controller;


import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.dto.BoardDto;
import freemarkerTest.demo.dto.Test;
import freemarkerTest.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class FreemarkerController {

    @Autowired
    private BoardService service;

    @GetMapping("/index")
    public String index() {
        return "freemarker/index";
    }

    @PostMapping("/test")
    public ResponseEntity<List<String>> test(@RequestBody Test test) {
        log.info("name : " + test.getName());
        log.info("location : " + test.getLocation());
        List<String> res = new ArrayList<>();
        res.add("success");

        return new ResponseEntity<List<String>>(res,HttpStatus.OK);
    }

    @GetMapping("/board")
    public ModelAndView boardList(Model model) {

//        List<Board> boardList = service.boardList();
//        model.addAttribute("boardList", service.boardList());
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("boardList", service.boardList());

        return new ModelAndView("/board/boardList", params);
    }

    @GetMapping("/postForm")
    public String boardPost() throws IOException {
        return "/board/postForm";
    }

    @PostMapping("/submit")
    public String regPost(BoardDto boardDto) {

        service.post(boardDto);
        return "freemarker/index";
    }

    @GetMapping("/board/{id}")
    public ModelAndView read(@PathVariable("id") Long id) {

        Map<String, Object> params = new LinkedHashMap<>();

        params.put("boards", service.readBoard(id));

        return new ModelAndView("/board/read", params);
    }
}
