package com.springboot.core.util;

import com.springboot.core.domain.common.PageArgs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class PaginationUtil {

    /**
     * 根据查询结果和分页大小， 计算出分页控件显示参数
     * @param req 从中取访问 url 和 query string
     * @param total 记录总数
     * @param pageSize 分页大小
     * @param currPage 当前页
     * @return 分页参数
     */
    public static PageArgs genPageArgs(HttpServletRequest req, long total, int pageSize, int currPage) {
        PageArgs args = new PageArgs();
        String url = req.getRequestURI();
        String q = StringUtil.isEmpty(req.getQueryString()) ? "" : req.getQueryString().replaceAll("&?pageIndex=\\d*", "");
        url = url.contains("?")? url: url+"?" + q + "&";
        args.setUrl(url);

        long pageCount = total / pageSize;
        pageCount = total % pageSize == 0 ? pageCount : pageCount + 1;

        args.setTotal(total);
        args.setPageSize(pageSize);
        args.setPageIndex(currPage);
        args.setPre(currPage - 1);
        args.setNext(currPage + 1 <= pageCount ? currPage + 1 : -1);
        args.setPageCount(pageCount);

        List<String> items = new ArrayList<>();
        if (pageCount <= 6) {
            for (int i = 1; i <= pageCount; i++) {
                items.add(String.valueOf(i));
            }
        } else if (currPage <= 4) {
            items.addAll(Arrays.asList("1", "2", "3", "4", "5", "6"));
            items.addAll(Arrays.asList("...", String.valueOf(pageCount)));
        } else if (pageCount - currPage <= 3) {
            items.addAll(Arrays.asList("1", "..."));
            items.addAll(Arrays.asList(
                    String.valueOf(pageCount - 4),
                    String.valueOf(pageCount - 3),
                    String.valueOf(pageCount - 2),
                    String.valueOf(pageCount - 1),
                    String.valueOf(pageCount)));
        } else {
            items.addAll(Arrays.asList("1", "..."));
            items.addAll(Arrays.asList(
                    String.valueOf(currPage - 1),
                    String.valueOf(currPage),
                    String.valueOf(currPage + 1)));
            items.addAll(Arrays.asList("...", String.valueOf(pageCount)));
        }
        args.setItems(items);
        return args;
    }


}
