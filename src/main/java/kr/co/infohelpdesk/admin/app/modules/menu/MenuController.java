package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final JPAQueryFactory queryFactory;
    private final String rootPath = "view/app/admin/menu/";

    private final MenuService menuService;


    public MenuController(JPAQueryFactory queryFactory, MenuService menuService) {
        this.queryFactory = queryFactory;
        this.menuService = menuService;
    }

    @RequestMapping("/index.do")
    public String menuIndex(Model model) {
        List<Menu> menus = menuService.findMenusByHierarchy("home1");

        model.addAttribute("menus", menus);
        return rootPath + "index";
    }

    @PostMapping("/details.do")
    public ResponseEntity<MenuDTO> getMenuDetails(@RequestBody Menu menu) {
        MenuDTO menuDTO = menuService.getMenuDetails(menu.getMenuIdx());
        if (menuDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(menuDTO);
    }


}