package com.cms.infohelpdesk.util.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class SearchBuilder {

    public static <T> BooleanBuilder buildSearchCriteria(PathBuilder<T> entityPath, String searchField, String searchValue) {
        BooleanBuilder searchCondition = new BooleanBuilder();
        if (searchField != null && !searchField.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
            StringPath path = entityPath.getString(searchField);
            searchCondition.and(path.containsIgnoreCase(searchValue));
        }
        return searchCondition;
    }

}
