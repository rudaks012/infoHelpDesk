package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    private final JPAQueryFactory queryFactory;
    private final QMenu menu = QMenu.menu;

    public List<Menu> findMenusByHierarchy(String homepageId) {

        // 모든 메뉴 조회
        List<Menu> allMenus = queryFactory
            .selectFrom(menu)
            .where(menu.homepageId.eq(homepageId))
            .orderBy(menu.menuDisplayOrder.asc())
            .fetch();

        // 부모 ID를 키로 하는 Map 생성
        Map<Menu, List<Menu>> menuMap = new HashMap<>();

        // Map을 채우면서 모든 메뉴 처리
        if (!allMenus.isEmpty()) {
            for (Menu oneMenu : allMenus) {
                menuMap.computeIfAbsent(oneMenu.getParentMenu(), k -> new ArrayList<>()).add(oneMenu);
            }
        }

        // 최상위 메뉴 목록 추출 및 하위 메뉴 설정
        List<Menu> topMenus = menuMap.get(null);
        if (topMenus != null) {
            topMenus.forEach(m -> setSubMenus(m, menuMap));
        }

        return topMenus != null ? topMenus : new ArrayList<>();
    }

    private void setSubMenus(Menu parent, Map<Menu, List<Menu>> menuMap) {
        List<Menu> subMenus = menuMap.getOrDefault(parent, new ArrayList<>());

        parent.setSubMenus(subMenus);

        for (Menu subMenu : subMenus) {
            setSubMenus(subMenu, menuMap);
        }
    }

    public MenuDTO getMenuDetails(Menu menu) {
        Menu getOneMenu = menuRepository.findById(menu.getMenuIdx()).orElse(null);
        if (getOneMenu == null) {
            return null;
        }
        MenuDTO menuDTO = new MenuDTO();
        switch (menu.getEditMode()) {
            case "ADD":
                return saveConvertToDTO(menuDTO, getOneMenu);
            case "MODIFY":
                return detailsConvertToDTO(menuDTO, getOneMenu);
            case "DELETE":
                return detailsConvertToDTO(menuDTO, getOneMenu);
            case "DETAIL":
                return detailsConvertToDTO(menuDTO, getOneMenu);
        }
        return menuDTO;
    }

    private MenuDTO detailsConvertToDTO(MenuDTO menuDTO, Menu menu) {
        basicConvertDTO(menuDTO, menu);

        menuDTO.setMenuPath(menu.getMenuPath());
        menuDTO.setMenuContentType(menu.getMenuContentType().name());
        menuDTO.setMenuVisible(menu.getMenuVisible());

        menuDTO.setMenuDisplayOrder(menu.getMenuDisplayOrder());

        return menuDTO;
    }


    private MenuDTO saveConvertToDTO(MenuDTO menuDTO, Menu menu) {
        basicConvertDTO(menuDTO, menu);

        menuDTO.setMenuPath("");
        menuDTO.setMenuContentType("HTML");
        menuDTO.setMenuVisible("Y");

        int maxSubMenusDisplay = menu.getSubMenus().stream()
                                     .mapToInt(Menu::getMenuDisplayOrder)
                                     .max()
                                     .orElse(0);

        menuDTO.setMenuDisplayOrder(maxSubMenusDisplay + 1);

        return menuDTO;
    }

    private void basicConvertDTO(MenuDTO menuDTO, Menu menu) {
        menuDTO.setMenuIdx(menu.getMenuIdx());
        menuDTO.setMenuTitle(menu.getMenuTitle());
        Optional.ofNullable(menu.getParentMenu())
                .ifPresent(parent -> menuDTO.setParentMenuTitle(parent.getMenuTitle()));
    }


}
