package com.nana.personalblogsystem.model.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.util.List;

/**
 * 文章创建VO
 * <hr/>
 * 文章创建VO，用于创建文章
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Getter
@SuppressWarnings("unused")
public class CommentVO {
    @NotBlank(message = "评论者名称不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9-_]{4,48}$", message = "创建名字格式不正确")
    public String cname;

    @NotBlank(message = "评论内容不能为空")
    public String cdesc;
}
