package com.nana.personalblogsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * Token 数据对象
 * <p>
 * 用于 Token 数据的存储。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDO {
    private String tokenUuid;
    private String userUuid;
    private Timestamp createdAt;
    private Timestamp expiredAt;
}
