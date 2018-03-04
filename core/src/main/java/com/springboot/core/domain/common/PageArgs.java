package com.springboot.core.domain.common;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PageArgs {
    private String url;
    private long total;
    private int pageSize;
    private int pageIndex;
    private long pageCount;
    private int pre;
    private int next;
    private List<String> items;
}