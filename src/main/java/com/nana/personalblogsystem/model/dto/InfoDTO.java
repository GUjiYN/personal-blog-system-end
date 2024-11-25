package com.nana.personalblogsystem.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    /**
     * 信息表主键
     */
    private int inid;
    /**
     * 信息表键
     */
    private String ikey;
    /**
     * 信息表值
     */
    private String ivalue;
    /**
     * 信息表创建时间
     */
    private String created_at;
}
