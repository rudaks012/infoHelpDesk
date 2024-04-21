package com.cms.infohelpdesk.board;

import com.cms.infohelpdesk.common.base.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

    final String rootPath = "view/board/";

    @Autowired
    private BoardRepository boardRepository;


    @GetMapping("/index.do")
    public String boardIndex() {

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

}
