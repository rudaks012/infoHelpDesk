package kr.co.infohelpdesk.admin.app.modules.menu.menuLogs;

import com.querydsl.core.annotations.QueryEntity;
import java.security.Timestamp;
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
import javax.persistence.Table;
import kr.co.infohelpdesk.admin.app.modules.menu.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;

@QueryEntity
@Entity
@Table(name = "menu_logs")
@NoArgsConstructor
@Getter
public class MenuLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuLogType action;

    @Column(nullable = false)
    private Timestamp actionTime;

    @Column(nullable = false)
    private Integer userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuId", nullable = false)
    private Menu menu;
}

