package kr.co.infohelpdesk.framework.util.paging;

import static org.springframework.data.domain.Sort.Direction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class PageableAndSort {

    private final Pageable pageable;
    private final String sortField;
    private final Direction sortDirection;

}