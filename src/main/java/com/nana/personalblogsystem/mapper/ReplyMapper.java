package com.nana.personalblogsystem.mapper;

import com.nana.personalblogsystem.model.entity.ReplyDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 回复映射器
 * <p>
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Mapper
public interface ReplyMapper {
    /**
     * 增加回复
     * @param
     */
    @Insert("INSERT INTO na_comment(aid, cid, cname, email, cdesc, replyid) VALUES (#{aid}, #{cid}, #{cname}, #{email}, #{cdesc}, #{replyid})")
    void addReply(ReplyDO replyDO);
}
