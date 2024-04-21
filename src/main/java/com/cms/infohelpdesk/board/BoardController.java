package com.cms.infohelpdesk.board;

import com.cms.infohelpdesk.common.base.DataTablesResponse;
import com.cms.infohelpdesk.common.base.MessageResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

    final String rootPath = "view/board/";

    @Autowired
    private BoardRepository boardRepository;


    @GetMapping("/index.do")
    public String boardIndex(Model model) {
//        List<Board> boardList = boardRepository.findAll();
//        model.addAttribute("boardList", boardList);
        return rootPath + "index";
    }

    @GetMapping("/edit.do")
    public String boardWrite() {
        return rootPath + "edit";
    }

    @PostMapping("/save.do")
    @ResponseBody
    public ResponseEntity<MessageResponse> savePost(@RequestBody Board board) {
        boardRepository.save(board);
        return ResponseEntity.ok(MessageResponse.save("index.do"));
    }

//    @GetMapping("/api/boards")
//    @ResponseBody
//    public DataTablesResponse<Board> list(@RequestParam("draw") int draw, @RequestParam("start") int start,
//        @RequestParam("length") int length,
//        @RequestParam("search[value]") String searchValue) {
//        Pageable pageable = PageRequest.of(start / length, length, Sort.Direction.ASC, "id");
//        Page<Board> boards = boardRepository.findByBbsSubjectContaining(searchValue, pageable);
//        return new DataTablesResponse<>(draw, boards.getTotalElements(), boards.getTotalElements(), boards.getContent());
//    }

    @GetMapping("/api/boards")
    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


}
