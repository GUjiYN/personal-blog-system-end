package com.nana.personalblogsystem.controller;


import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.dto.TagDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.service.ArticleService;
import com.nana.personalblogsystem.service.InfoService;
import com.nana.personalblogsystem.service.TagService;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 标签控制器
 * <hr/>
 * 标签控制器，用于标签的增删改查
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tag")
public class TagController {
    private final TagService tagService;
    private final TokenService tokenService;
    private final InfoService infoService;

    /**
     * 获取标签
     * <p>
     *  用于获取所有标签
     */
    @GetMapping("/list")
    public @NotNull ResponseEntity<BaseResponse<CustomPage<TagDTO>>> getAllTags(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
       CustomPage<TagDTO> tagList = tagService.getAllTags(page, size);
       return ResultUtil.success("获取成功", tagList);
    }


    /**
     * 删除标签
     * <p>
     * 用于删除标签
     * @param tname
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public @NotNull ResponseEntity<BaseResponse<Void>> deleteTags(
            @RequestParam String tname,
            HttpServletRequest request
    ) {
        if(tname.isBlank()) {
            throw new BusinessException("标签名不能为空", ErrorCode.BODY_ERROR);
        }
        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = tokenService.selectToken(token);
        InfoDTO infoDTO = infoService.selectInfoByKey("system_super_admin_uuid");
        if (tokenDTO.getUserUuid().equals(infoDTO.getIvalue())) {
            tagService.deleteTag(tname);
            return ResultUtil.success("删除成功");
        }
        throw new BusinessException("无权限", ErrorCode.BODY_ERROR);
    }
    }


