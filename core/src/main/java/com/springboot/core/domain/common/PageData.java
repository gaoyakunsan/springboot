package com.springboot.core.domain.common;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PageData<T> {
    private long totalSize;
    private long totalPage;
    private int currentPage;
    private int pageSize;

    private List<T> list;

    public  PageData<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public PageData() {
    }

    public PageData(List<T> list, int currentPage, int pageSize, long totalSize) {
        this.list = list;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
    }
}
