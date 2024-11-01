package com.nana.personalblogsystem.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nana.personalblogsystem.model.CustomPage;
import com.nana.personalblogsystem.model.dto.ArticleDTO;
import com.nana.personalblogsystem.model.dto.InfoDTO;
import com.nana.personalblogsystem.model.dto.TokenDTO;
import com.nana.personalblogsystem.model.entity.ArticleDO;
import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.nana.personalblogsystem.service.AuthService;
import com.nana.personalblogsystem.service.InfoService;
import com.nana.personalblogsystem.service.TokenService;
import com.nana.personalblogsystem.util.CopyUtil;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * 获取文章
     * <p>
     *  用于获取文章列表
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<CustomPage<ArticleDTO>>> getArticleList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size
    ) {
        // 获取分页后的文章数据
        Page<ArticleDO> articleList = articleService.getArticleList(page, size);

        // 将 ArticleDO 转换为 ArticleDTO
        CustomPage<ArticleDTO> newArticleList = new CustomPage<>();
        CopyUtil.pageDoCopyToDTO(articleList, newArticleList, ArticleDTO.class);

        // 返回成功结果
        return ResultUtil.success("操作成功", newArticleList);
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
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<Void>> deleteArticle(
            @RequestParam("aid") String aid,
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
