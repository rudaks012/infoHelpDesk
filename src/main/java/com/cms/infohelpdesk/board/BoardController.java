package com.cms.infohelpdesk.board;

import com.cms.infohelpdesk.common.base.MessageResponse;
import com.cms.infohelpdesk.common.paging.PageDetails;
import com.cms.infohelpdesk.common.paging.PagingUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;
    private final String rootPath = "view/board/";

    @Autowired
    public BoardController(BoardRepository boardRepository, JPAQueryFactory queryFactory) {
        this.boardRepository = boardRepository;
        this.queryFactory = queryFactory;
    }
    @GetMapping("/index.do")
    public String boardIndex(Model model, @ModelAttribute Board board, HttpServletRequest request) {

        PageDetails pageDetails = PagingUtils.getPageDetails(request, boardRepository, board, queryFactory, Board.class);
        model.addAttribute("pageDetails", pageDetails);
        model.addAttribute("board", board);
        model.addAttribute("boardList", pageDetails.getPageData());

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

    @GetMapping("/api/boards")
    public Page<Board> getBoards(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


}
