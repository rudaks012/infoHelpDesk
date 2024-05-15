package com.cms.infohelpdesk.common.base;

import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {


    private String sortValue;

    private String orderValue;

    private String pageValue;

    private String sizeValue;
}
