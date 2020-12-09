package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.dao.ArticleDao;
import cn.tqyao.blog.web.dto.ArticleBodyDTO;
import cn.tqyao.blog.web.dto.ArticleDTO;
import cn.tqyao.blog.web.dto.ArticleUpdateBaseDTO;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import cn.tqyao.blog.web.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章列表 前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Api(tags = "文章-列表")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "添加文章")
    @PostMapping
    public Result<Boolean> add(@RequestBody ArticleDTO dto) {
        return Result.status(articleService.addArticle(dto));
    }

    @ApiOperation(value = "修改文章内容", notes = "article_body")
    @PutMapping("/body/{id}")
    public Result<Boolean> updateBody(@ApiParam(value = "文章ID或文章内容ID") @PathVariable String id,
                                      @RequestBody ArticleBodyDTO dto) {
        return Result.status(articleService.updateBody(id, dto));
    }

    @ApiOperation(value = "修改文章基本信息", notes = "文章标题、概要")
    @PutMapping("/base/{id}")
    public Result<Boolean> updateArticle(@ApiParam(value = "文章ID或文章内容ID") @PathVariable String id,
                                         @RequestBody ArticleUpdateBaseDTO dto) {
        return Result.status(articleService.updateBase(id, dto));
    }

    @ApiOperation(value = "修改文章权重", notes = "局部修改")
    @PatchMapping("/weight/{article-id}/{flag}")
    public Result<Boolean> updateWeight(@ApiParam(value = "文章ID")@PathVariable("article-id") String articleId,
                                        @ApiParam(value = "权重：0 -> 普通；1 -> 置顶")@PathVariable("flag") Integer weight){
        return Result.status(articleService.updateWeight(articleId, weight));
    }

    @ApiOperation(value = "是否是草稿", notes = "局部修改")
    @PatchMapping("/draft/{article-id}/{flag}")
    public Result<Boolean> updateDraft(@ApiParam(value = "文章ID")@PathVariable("article-id") String articleId,
                                        @ApiParam(value = "是否草稿:0 -> 否；1 -> 是")@PathVariable("flag") Integer draft){
        return Result.status(articleService.updateDraft(articleId, draft));
    }



}
