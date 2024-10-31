package com.nana.personalblogsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;


/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    /**
     * token表主键
     */
    private String tokenUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
    /**
     * token创建时间
     */
    private Timestamp createdAt;
    /**
     * token过期时间
     */
    private Timestamp expiredAt;
}
