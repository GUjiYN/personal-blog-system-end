package com.nana.personalblogsystem.controller;

import com.nana.personalblogsystem.model.entity.TokenDO;
import com.nana.personalblogsystem.model.vo.ArticleVO;
import com.nana.personalblogsystem.model.vo.AuthRegisterVO;
import com.nana.personalblogsystem.service.ArticleService;
import com.nana.personalblogsystem.service.TokenService;
import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;
    private final TokenService tokenService;
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
        String token = request.getHeader("token");
        TokenDO tokenDO = tokenService.verifyToken(token);
        if (tokenDO == null) {

        }
        articleService.createArticle(articleVO,request);
        return ResultUtil.success("创建成功");
    }

    /**
     * 更新文章
     * <p>
     * 用于更新文章
     */
    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Void>> updateArticle(
            @RequestBody @Validated ArticleVO articleVO,
            @RequestParam("aid") String aid
    ){
        articleService.updateArticle(articleVO, aid);
        return ResultUtil.success("更新成功");
    }

    /**
     * 删除文章
     * <p>
     * 用于删除文章
     */
    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse<Void>> deleteArticle(
            @RequestParam("aid") String aid
    ){

        return ResultUtil.success("删除成功");
    }
}
