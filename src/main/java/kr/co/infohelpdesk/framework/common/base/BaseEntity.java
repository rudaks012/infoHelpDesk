package kr.co.infohelpdesk.framework.common.base;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass

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
}
