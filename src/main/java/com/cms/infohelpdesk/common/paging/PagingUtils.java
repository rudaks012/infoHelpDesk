package com.cms.infohelpdesk.common.paging;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.HttpServletRequest;

public class PagingUtils {

    public static PageDetails getPageDetails(HttpServletRequest request, JpaRepository repository, Class entityType) {
        Pageable pageable = extractPageable(request);
        Page<?> page = repository.findAll(pageable);
        return calculatePageDetails(page);
    }

    private static Pageable extractPageable(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "0");
        System.out.println("page = " + page);
        int size = Integer.parseInt(request.getParameter("size") != null ? request.getParameter("size") : "10");
        System.out.println("size = " + size);
        String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "bbsSubject";

        return PageRequest.of(page, size, Sort.by(sort));
    }

    private static PageDetails calculatePageDetails(Page<?> page) {
        int currentPage = page.getNumber();
        int totalPages = page.getTotalPages();
        int pageSize = page.getSize();
        long totalElements = page.getTotalElements();
        int startPage = calculateStartPage(currentPage, totalPages);
        int endPage = calculateEndPage(currentPage, totalPages);
        boolean hasPrevious = currentPage > 0;
        boolean hasNext = currentPage < totalPages - 1;

        int startEntry = currentPage * pageSize + 1;
        int endEntry = Math.min(startEntry + pageSize - 1, (int) totalElements);

        return new PageDetails(startPage, endPage, currentPage, totalPages, hasPrevious, hasNext, startEntry, endEntry, totalElements, page);
    }

    private static int calculateStartPage(int currentPage, int totalPages) {
        return Math.max(0, currentPage - 3);
    }

    private static int calculateEndPage(int currentPage, int totalPages) {
        return Math.min(currentPage + 3, totalPages - 1);
    }
}
