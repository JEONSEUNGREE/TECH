package freemarkerTest.demo.controller;


import freemarkerTest.demo.domain.Board;
import freemarkerTest.demo.domain.BoardDto;
import freemarkerTest.demo.service.BoardService;
import freemarkerTest.demo.util.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/list")
    public ModelAndView list(Model model) {

        TemplateUtil templateUtil = new TemplateUtil();

        List<Board> boards = boardService.boardList();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        List<BoardDto> boardList = boards.stream().map(board -> new BoardDto(board.getId(), board.getTitle(), dateFormat.format(board.getRegDate()))).collect(Collectors.toList());

//      Map의 참조변수명은 중요하지않음
        Map<String, Object> params = new LinkedHashMap<>();
//      Map형태의 Key, Value 넣기
        params.put("boardList", boardList);

        model.addAttribute("header", templateUtil.template("header", new LinkedHashMap<>()));
        model.addAttribute("footer", templateUtil.template("footer", new LinkedHashMap<>()));

        return new ModelAndView("list", params);
    }

    @GetMapping(value = "/read/{id}")
    public String read(@PathVariable("id")Long id, Model model) {
        Board board = boardService.boardRead(id);
        model.addAttribute("board", board);
        return "readBoard";
    }

    @PostMapping(value = "/post")
    public String postBoard(Board board) {

        boardService.post(board);

        return "list";
    }

    @GetMapping(value = "/delboard/{id}")
    public String delBoard(@PathVariable("id")Long id) {
        boardService.boardDelete(id);
        return "list";
    }

}
