package com.nana.personalblogsystem.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.sql.Timestamp;

/**
 * Reply 数据对象
 * <p>
 * 用于 Reply 数据的存储。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDO {
    private String aid;
    private String cid;
    private String cname;
    private String email;
    private String cdesc;
    private Timestamp createdAt;
    private String replyid;
}
