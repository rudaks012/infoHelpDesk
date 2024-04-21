package com.cms.infohelpdesk.common.base;

import java.util.List;
import lombok.Getter;

@Getter
public class DataTablesResponse<T> {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;

    public DataTablesResponse(int draw, long recordsTotal, long recordsFiltered, List<T> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
    // Getters and Setters
}


