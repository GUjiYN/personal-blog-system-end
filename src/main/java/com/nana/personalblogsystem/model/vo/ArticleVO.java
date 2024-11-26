package com.nana.personalblogsystem.model.vo;/*
 * ***************************************************************************************
 * author: FrontLeaves Co,.Ltd.(https://www.frontleaves.com)
 * about:
 *   The project contains the source code of com.te.business.
 * licenseStatement:
 *   Copyright (c) 2016-2024 FrontLeaves Co,.Ltd. All rights reserved.
 * ***************************************************************************************
 */



import jakarta.validation.constraints.*;
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
public class ArticleVO {

    @NotBlank(message = "文章标题不能为空")
    @Pattern(regexp = "^[\\p{L}0-9\\-\\_\\#\\*\\[\\]\\(\\)\\+\\.\\s]{1,48}$", message = "创建名字格式不正确")
    public String title;


    @NotBlank(message = "文章内容不能为空")
    public String description;

    @NotNull(message = "文章标签不能为空")
    public List<String> tags;

    public String image;
}
