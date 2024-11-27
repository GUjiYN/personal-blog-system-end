package com.nana.personalblogsystem.controller;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.nana.personalblogsystem.service.InfoService;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 文章控制器
 * <hr/>
 * 文章控制器，用于文章的增删改查
 * @version v1.0.0
 * @since v1.0.0
 * @author nana
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;
    private final TokenService tokenService;
    private final InfoService infoService;


    /**
     * 获取文章列表
     * <p>
     *  用于获取文章列表
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<CustomPage<ArticleDTO>>> getArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        CustomPage<ArticleDTO> articleList = articleService.getArticleList(page, size);
        return ResultUtil.success("操作成功", articleList);
    }


    /**
     * 获取文章详细信息
     * <p>
     * 用于获取文章详细信息
     */
    @GetMapping("/{aid}")
    public ResponseEntity<BaseResponse<ArticleDTO>> getArticleDetails(
            @PathVariable("aid") String aid
    ) {
        ArticleDTO articleDO = articleService.getArticleDetails(aid);
        return ResultUtil.success("获取文章详细信息成功", articleDO);
    }


    /**
     * 查询文章
     * <p>
     * 用于文章列表的模糊查询
     */
    @GetMapping("/search")
    public @NotNull ResponseEntity<BaseResponse<CustomPage<ArticleDTO>>> getArticleByTitleOrDesc(
            @RequestParam String keyword,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        log.debug("111");
        CustomPage<ArticleDTO> getArticleByTitleOrDesc = articleService.getArticlesByTitleOrDesc(keyword,page,size);
        if (getArticleByTitleOrDesc == null) {
            throw new BusinessException("无文章", ErrorCode.BODY_ERROR);
        }
        return ResultUtil.success("查询成功", getArticleByTitleOrDesc);
    }

    /**
     * 根据标签查询文章
     * @param tname
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/tname")
    public @NotNull ResponseEntity<BaseResponse<CustomPage<ArticleDTO>>> getArticleByTag(
            @RequestParam String tname,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        CustomPage<ArticleDTO> getArticlesByTags = articleService.getArticlesByTags(tname, page, size);
        if (getArticlesByTags == null) {
            throw new BusinessException("无文章", ErrorCode.BODY_ERROR);
        }
        return ResultUtil.success("查询成功", getArticlesByTags);
    }

    /**
     * 创建文章
     * <p>
     *用于新增文章
     *
     */
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Void>> createArticle(
            @RequestBody @Validated ArticleVO articleVO,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = tokenService.selectToken(token);
        InfoDTO infoDTO = infoService.selectInfoByKey("system_super_admin_uuid");
        if (tokenDTO.getUserUuid().equals(infoDTO.getIvalue())) {
            articleService.createArticle(articleVO, request);
            return ResultUtil.success("创建成功");
        }
        throw new BusinessException("无权限", ErrorCode.BODY_ERROR);
    }

    /**
     * 更新文章
     * <p>
     * 用于更新文章
     */
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Void>> updateArticle(
            @RequestBody @Validated ArticleVO articleVO,
            @RequestParam("aid") String aid,
            HttpServletRequest request
    ){
        if (aid.isBlank()) {
            throw new BusinessException("文章id不能为空", ErrorCode.BODY_ERROR);
        }
        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = tokenService.selectToken(token);
        InfoDTO infoDTO = infoService.selectInfoByKey("system_super_admin_uuid");
        if (tokenDTO.getUserUuid().equals(infoDTO.getIvalue())) {
            articleService.updateArticle(articleVO, aid);
            return ResultUtil.success("更新成功");
        }
        throw new BusinessException("无权限", ErrorCode.BODY_ERROR);
    }

    /**
     * 删除文章
     * <p>
     * 用于删除文章
     */
    @DeleteMapping("/delete/{aid}")
    public ResponseEntity<BaseResponse<Void>> deleteArticle(
            @PathVariable("aid") String aid,
            HttpServletRequest request
    ){
        if(aid.isBlank()){
            throw new BusinessException("文章id不能为空", ErrorCode.BODY_ERROR);
        }
        String token = request.getHeader("Authorization");
        TokenDTO tokenDTO = tokenService.selectToken(token);
        InfoDTO infoDTO = infoService.selectInfoByKey("system_super_admin_uuid");
        if (tokenDTO.getUserUuid().equals(infoDTO.getIvalue())) {
            articleService.deleteArticle(aid);
            return ResultUtil.success("删除成功");
        }
        throw new BusinessException("无权限", ErrorCode.BODY_ERROR);
    }
}
