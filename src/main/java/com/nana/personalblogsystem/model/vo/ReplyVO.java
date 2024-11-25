package com.nana.personalblogsystem.model.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * 回复VO
 * <hr/>
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Getter
public class ReplyVO {
    @NotBlank(message = "评论的文章id不能为空")
    public String aid;

    @NotBlank(message = "评论者名称不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9-_]{4,48}$", message = "创建名字格式不正确")
    public String cname;

    @Email(message = "邮箱格式不正确")
    public String email;

    @NotBlank(message = "回复内容不能为空")
    public String cdesc;

    @NotBlank(message = "回复id不能为空")
    public String replyid;

    public Integer likes;

    public Integer dislikes;
}
