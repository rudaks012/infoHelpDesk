package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final JPAQueryFactory queryFactory;
    private final String rootPath = "view/app/admin/menu/";


    public MenuController(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @RequestMapping("/index.do")
    public String menuIndex() {
        return rootPath + "index";
    }
}