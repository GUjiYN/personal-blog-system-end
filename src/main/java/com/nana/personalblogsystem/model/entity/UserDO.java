package com.nana.personalblogsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;


/**
 * User 数据对象
 * <p>
 * 用于 User 数据的存储。
 *
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
    /**
     * 用户表主键
     */
    private String uuid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户当前密码
     */
    private String password;
    /**
     * 角色
     */
    private String role;
    /**
     * 创建时间
     */
    private Timestamp createdAt;
    /**
     * 修改时间
     */
    private Timestamp updatedAt;
}
