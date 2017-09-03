package com.ls.dto;

import java.io.Serializable;
import java.util.List;

public class DatatableResponseVO<T> implements Serializable {
    public Long recordsFiltered;
    public Long recordsTotal;
    public List<T> data;

    public DatatableResponseVO(Long recordsFiltered, Long recordsTotal, List<T> data) {
        this.recordsFiltered = recordsFiltered;
        this.recordsTotal = recordsTotal;
        this.data = data;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
