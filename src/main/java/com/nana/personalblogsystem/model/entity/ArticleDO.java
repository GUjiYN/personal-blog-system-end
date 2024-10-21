package com.nana.personalblogsystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.security.Timestamp;

/**
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDO {
    private String aid;
    private String authid;
    private String title;
    private String tags;
    private String desc;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
