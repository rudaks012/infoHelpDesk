package com.cms.infohelpdesk.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class PageDetails {

    private final int startPage;
    private final int endPage;
    private final int currentPage;
    private final int totalPages;
    private final boolean hasPrevious;
    private final boolean hasNext;
    private final int startEntry;
    private final int endEntry;
    private final long totalElements;
    private final Page<?> pageData;
}
