package kr.co.infohelpdesk.admin.app.modules.menu;

import lombok.Data;

@Data
public class MenuDTO {
    private Integer menuIdx;
    private String menuTitle;
    private String menuPath;
    private String menuContentType;
    private Boolean menuVisible;
    private Integer menuDisplayOrder;
    private String parentMenuTitle; // 상위 메뉴 제목 추가

}
