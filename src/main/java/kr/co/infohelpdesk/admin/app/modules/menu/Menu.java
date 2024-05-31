package kr.co.infohelpdesk.admin.app.modules.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.querydsl.core.annotations.QueryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import kr.co.infohelpdesk.framework.common.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@QueryEntity
@Entity
@Table(name = "menus")
@NoArgsConstructor
@Getter
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuIdx; //메뉴의 고유 식별자',

    @Column(nullable = false, length = 255)
    private String homepageId; //홈페이지ID

    @Column(nullable = false, length = 255)
    private String menuTitle; //메뉴의 제목

    @Column(nullable = false, length = 255)
    private String menuPath; //메뉴의 경로

    @Column(nullable = false)
    private Integer menuDisplayOrder; //메뉴의 표시 순서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuParentId")
    @JsonBackReference
    private Menu parentMenu; //상위 메뉴

    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("menuDisplayOrder ASC") // 하위 메뉴를 표시 순서에 따라 정렬
    @JsonManagedReference
    private List<Menu> subMenus = new ArrayList<>();

    @Column(nullable = false)
    private String menuVisible; //메뉴의 노출 여부

    @Column(nullable = false)
    private String menuMobileVisible; //메뉴의 모바일 노출 여부

    @Column(nullable = false)
    private String menuActive; //메뉴의 활성화 여부

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuContentType menuContentType; //메뉴의 컨텐츠 타입

    @Column(length = 255)
    private String menuInternalLink; //메뉴의 내부 링크

    @Column(length = 255)
    private String menuExternalLink; //메뉴의 외부 링크

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

}

