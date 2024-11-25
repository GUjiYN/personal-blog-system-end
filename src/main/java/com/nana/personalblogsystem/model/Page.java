package com.nana.personalblogsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页
 * <p>
 * 该类用于定义分页；
 * 该类包含以下字段：
 * <ul>
 *     <li>{@link List<T>} records</li>
 *     <li>{@link Long} total</li>
 *     <li>{@link Long} size</li>
 *     <li>{@link Long} current</li>
 *     <li>{@link Long} pages</li>
 * </ul>
 * 该类用于分页查询时返回分页数据。
 *
 * @param <T> 分页数据类型
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    private List<T> records;
    private Long total;
    private Long size;
    private Long current;
    private Long pages;
}
