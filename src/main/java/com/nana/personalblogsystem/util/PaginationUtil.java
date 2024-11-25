package com.nana.personalblogsystem.util;

import com.nana.personalblogsystem.model.CustomPage;

import java.util.List;

public class PaginationUtil {

    public static <T> CustomPage<T> paginate(List<T> data, int page, int size) {
        // 计算总记录数
        long total = data.size();

        // 计算起始索引和结束索引
        int fromIndex = Math.max(0, (page - 1) * size);
        int toIndex = Math.min(fromIndex + size, data.size());

        // 截取当前页的数据
        List<T> records = data.subList(fromIndex, toIndex);

        // 构建 CustomPage 对象
        CustomPage<T> customPage = new CustomPage<>();
        customPage.setRecords(records);
        customPage.setTotal(total);
        customPage.setSize((long) size);
        customPage.setCurrent((long) page);
        customPage.setPages((total + size - 1) / size); // 计算总页数

        return customPage;
    }
}

