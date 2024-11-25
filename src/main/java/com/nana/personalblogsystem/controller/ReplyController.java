package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.entity.ReplyDO;
import com.nana.personalblogsystem.model.vo.ReplyVO;
import com.nana.personalblogsystem.service.ReplyService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回复控制器
 * <hr/>
 * 回复控制器，用于回复的增删改查
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 增加回复
     * <p>
     * 用于增加回复
     */
    @Transactional
    @PostMapping("/add")
    public @NotNull ResponseEntity<BaseResponse<ReplyDO>> addReply(
            @RequestBody @Validated ReplyVO replyVO
            ) {
        ReplyDO newReply = replyService.addReply(replyVO);
        return ResultUtil.success("发布成功", newReply);
    }
}
