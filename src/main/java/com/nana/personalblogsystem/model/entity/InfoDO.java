package com.nana.personalblogsystem.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Info 数据对象
 * <p>
 * 用于 Info 数据的存储。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class InfoDO {
    private Integer inid;
    private String key;
    private String value;
    private String created_at;
}
