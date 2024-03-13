package com.zhongfeng.shortlink.utils;

import cn.hutool.core.collection.CollUtil;
import com.zhongfeng.common.db.model.PageData;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类描述
 *
 * @auther fanjun
 */
public class PageDataUtils {

    public static <T, R> PageData<R> changePageData(PageData<T> pageData, Function<T, R> changeFunc) {
        PageData<R> retPageData = new PageData<>();
        retPageData.setPageNo(pageData.getPageNo());
        retPageData.setPageSize(pageData.getPageSize());
        retPageData.setPages(pageData.getPages());
        retPageData.setTotal(pageData.getTotal());
        List<T> dataList = pageData.getData();
        if (CollUtil.isNotEmpty(dataList)) {
            retPageData.setData(dataList.stream().map(changeFunc).collect(Collectors.toList()));
        }
        return retPageData;
    }
}
