package kr.co.infohelpdesk.admin.app.modules.menu;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final JPAQueryFactory queryFactory;
    private final QMenu menu = QMenu.menu;

    @Autowired
    private MenuRepository menuRepository;


    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Integer id) {
        menuRepository.deleteById(id);
    }

    // 계층형 메뉴 조회
    public List<Menu> findMenusByHierarchy(String homepageId) {
        return queryFactory
            .selectFrom(menu)
            .where(menu.parentMenu.isNull()
                                  .and(menu.homepageId.eq(homepageId)))
            .orderBy(menu.menuDisplayOrder.asc())
            .fetch();
    }


    // 메뉴 노출 여부에 따른 조회
    public List<Menu> findVisibleMenus(String homepageId, boolean isVisible) {
        return queryFactory
            .selectFrom(menu)
            .where(menu.menuVisible.eq(isVisible)
                                   .and(menu.homepageId.eq(homepageId)))
            .orderBy(menu.menuDisplayOrder.asc())
            .fetch();
    }
}
