package com.cms.infohelpdesk.util.paging;

import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.by;

import com.cms.infohelpdesk.common.base.BaseEntity;
import com.cms.infohelpdesk.util.search.SearchBuilder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class PagingUtils {

    public static <T extends BaseEntity> PageDetails getPageDetails(JpaRepository<T, ?> repository, T entity, JPAQueryFactory queryFactory, Class<T> type) {
        PageableAndSort pageableAndSort = extractPageable(entity);
        PathBuilder<T> entityPath = new PathBuilder<>(type, type.getSimpleName().toLowerCase());

        BooleanBuilder searchCondition = SearchBuilder.buildSearchCriteria(entityPath, entity.getSearchField(), entity.getSearchValue());

        String sortField = pageableAndSort.getSortField();
        StringPath pathForSortField = entityPath.getString(sortField);
        OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(Order.valueOf(pageableAndSort.getSortDirection().name()), pathForSortField);

        QueryResults<T> results = queryFactory.selectFrom(entityPath)
                                              .where(searchCondition)
                                              .offset(pageableAndSort.getPageable().getOffset())
                                              .limit(pageableAndSort.getPageable().getPageSize())
                                              .orderBy(orderSpecifier)
                                              .fetchResults();

        List<T> content = results.getResults();
        long total = results.getTotal();

        Page<T> page = new PageImpl<>(content, pageableAndSort.getPageable(), total);

        return calculatePageDetails(page, pageableAndSort.getSortField(), pageableAndSort.getSortDirection());
    }


    private static <T extends BaseEntity> PageableAndSort extractPageable(T entity) {

        // 페이지와 사이즈 파라미터 처리
        String pageParam = entity.getPageValue();
        int page = 0; // 기본값 0
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 페이지 번호, 기본값 사용: 0");
            }
        }

        String sizeParam = entity.getSizeValue();
        int size = 10; // 기본값 10
        if (sizeParam != null && !sizeParam.isEmpty()) {
            try {
                size = Integer.parseInt(sizeParam);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 크기 번호, 기본값 사용: 10");
            }
        }

        // 정렬 파라미터 처리
        Sort sort = by(Direction.DESC, "bbsRegdate"); // 기본 정렬 설정
        if (entity.getSortValue() == null || entity.getSortValue().trim().isEmpty()) {
            entity.setSortValue("bbsRegdate");
        }
        String sortParam = entity.getSortValue();

        Direction direction;
        String sortOrder = entity.getOrderValue();
        if (sortOrder != null && sortOrder.equalsIgnoreCase("asc")) {
            direction = Direction.ASC;
        } else {
            direction = Direction.DESC;
        }
        sort = by(direction, sortParam);

        return new PageableAndSort(PageRequest.of(page, size, sort), sortParam, direction);

    }


    private static PageDetails calculatePageDetails(Page<?> page, String sortField, Direction sortDirection) {
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

        return new PageDetails(startPage, endPage, currentPage, totalPages, hasPrevious, hasNext, startEntry, endEntry, totalElements, page, sortField, sortDirection);
    }

    private static int calculateStartPage(int currentPage, int totalPages) {
        return Math.max(0, currentPage - 3);
    }

    private static int calculateEndPage(int currentPage, int totalPages) {
        return Math.min(currentPage + 3, totalPages - 1);
    }
}
