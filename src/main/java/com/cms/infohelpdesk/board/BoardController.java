package com.cms.infohelpdesk.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/boardIndex")
    public String boardIndex() {
        return "view/board/index";
    }

}
