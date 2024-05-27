package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        for (Menu m : allMenus) {
            menuMap.computeIfAbsent(m.getParentMenu(), k -> new ArrayList<>()).add(m);
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

    public MenuDTO getMenuDetails(Integer menuIdx) {
        Menu menu = menuRepository.findById(menuIdx).orElse(null);
        if (menu == null) {
            return null;
        }
        return convertToDTO(menu);
    }

    public MenuDTO convertToDTO(Menu menu) {
        MenuDTO dto = new MenuDTO();
        dto.setMenuIdx(menu.getMenuIdx());
        dto.setMenuTitle(menu.getMenuTitle());
        dto.setMenuPath(menu.getMenuPath());
        dto.setMenuContentType(menu.getMenuContentType().name());
        dto.setMenuVisible(menu.getMenuVisible());
        dto.setMenuDisplayOrder(menu.getMenuDisplayOrder());
        if (menu.getParentMenu() != null) {
            dto.setParentMenuTitle(menu.getParentMenu().getMenuTitle());
        }
        return dto;
    }


}
