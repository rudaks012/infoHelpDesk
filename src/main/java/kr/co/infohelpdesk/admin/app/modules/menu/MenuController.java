package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return Optional.ofNullable(menuService.getMenuDetails(menu))
                       .map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public String saveMenu(@ModelAttribute Menu menu, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menu", menu);
            return rootPath + "index";  // 에러가 있을 경우 다시 메뉴 목록으로 리다이렉트
        }
        return "redirect:/menus/index.do";  // 저장 후 메뉴 목록으로 리다이렉트
    }


}