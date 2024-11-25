package com.nana.personalblogsystem.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    /**
     * 标签表主键
     */
    private int tid;
    /**
     * 标签名称
     */
    private String tname;
}
