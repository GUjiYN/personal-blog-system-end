package com.nana.personalblogsystem.util;

import com.nana.personalblogsystem.model.CustomPage;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    /**
     * 复制分页对象
     * <p>
     * 该方法用于将源对象列表复制到目标对象列表，支持泛型处理；
     * 需要提供 {@code 源对象}、{@code 目标对象}、{@code 目标对象类}。
     *
     * @param source   源对象 List 数据
     * @param target   目标对象分页数据
     * @param dtoClass 目标对象类
     * @param <T>      源对象类型
     * @param <E>      目标对象类型
     */
    public static <T, E> void pageDoCopyToDTO(
            @NotNull List<T> source,
            @NotNull CustomPage<E> target,
            @NotNull Long page,
            @NotNull Long size,
            @NotNull Class<E> dtoClass
    ) {
        // 初始化目标对象的分页数据
        target
                .setRecords(new ArrayList<>())
                .setTotal((long) source.size())
                .setSize(size)
                .setPages(1L)
                .setCurrent(0L);
        // 如果源对象有数据，则进行对象复制
        if (!source.isEmpty()) {
            long limit = 0L;
            for (int i = (int) ((page - 1) * size); i < source.size(); i++) {
                if (limit >= size) {
                    break;
                }
                try {
                    limit++;
                    E dto = dtoClass.getDeclaredConstructor().newInstance();
                    BeanUtils.copyProperties(source.get(i), dto);
                    target.getRecords().add(dto);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("DTO 对象实例化失败: " + e.getMessage());
                }
            }
            target
                    .setCurrent((long) target.getRecords().size())
                    .setPages((long) target.getRecords().size() / size + 1);
        }
    }
}
