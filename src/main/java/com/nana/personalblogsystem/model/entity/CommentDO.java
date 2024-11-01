package com.nana.personalblogsystem.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Comment 数据对象
 * <p>
 * 用于 Comment 数据的存储。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentDO {
    private String cid;
    private String cname;
    private String cdesc;
    private String created_at;
    private String reply;
}
