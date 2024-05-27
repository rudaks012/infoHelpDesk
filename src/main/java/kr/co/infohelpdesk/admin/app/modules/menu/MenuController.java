package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final JPAQueryFactory queryFactory;
    private final String rootPath = "view/app/admin/menu/";

    private final MenuRepository menuRepository;
    private final MenuService menuService;


    public MenuController(JPAQueryFactory queryFactory, MenuRepository menuRepository, MenuService menuService) {
        this.queryFactory = queryFactory;
        this.menuRepository = menuRepository;
        this.menuService = menuService;
    }

    @RequestMapping("/index.do")
    public String menuIndex(Model model) {
        List<Menu> menus = menuService.findMenusByHierarchy("home1");

        model.addAttribute("menus", menus);
        return rootPath + "index";
    }
}