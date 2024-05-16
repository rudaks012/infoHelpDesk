package kr.co.infohelpdesk.admin.app.modules.menu.menuHistory;

import com.querydsl.core.annotations.QueryEntity;
import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import kr.co.infohelpdesk.admin.app.modules.menu.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@QueryEntity
@Entity
@Table(name = "menu_histories")
@NoArgsConstructor
@Getter
public class MenuHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private Timestamp changedAt;

    @Column(nullable = false)
    private Integer changedBy;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String changeDescription;
}

