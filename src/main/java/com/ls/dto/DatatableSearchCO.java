package com.ls.dto;

public class DatatableSearchCO {
    String sortColumn = "id";

    String order = "asc";

    Integer max = 10;
    Integer offset = 0;

    Integer pageIndex = 1;

    public String getSortColumn() {
        return sortColumn;
    }

    public String getOrder() {
        return order;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
