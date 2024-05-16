package kr.co.infohelpdesk.framework.common.base;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Transient
    private String sortValue;
    @Transient
    private String orderValue;
    @Transient
    private String pageValue;
    @Transient
    private String sizeValue;
    @Transient
    private String searchField;
    @Transient
    private String searchValue;
    @Transient
    private String editMode = "ADD";

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdDate;
}
