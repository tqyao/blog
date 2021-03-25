package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.dao.ArticleDao;
import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleTagRelation;
import cn.tqyao.blog.web.dto.*;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.vo.ArticleDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Set;

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
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    //TODO：分类改写
    @ApiOperation(value = "添加文章")
    @PostMapping("/article")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article-id", value = "文章ID", required = true,
                    paramType = "path", dataTypeClass = String.class, dataType = "String"),
            @ApiImplicitParam(name = "flag", value = "是否草稿:0 -> 否；1 -> 是", required = true,
                    defaultValue = "1", allowableValues = "0,1",
                    paramType = "path", dataTypeClass = Integer.class, dataType = "Integer")
    })
    public Result<Boolean> updateDraft(@ApiParam(value = "文章ID")@PathVariable("article-id") String articleId,
                                       @PathVariable("flag") Integer draft){
        return Result.status(articleService.updateDraft(articleId, draft));
    }


    @ApiOperation(value = "为文章添加标签")
    @PostMapping("/tags")
    public Result<Boolean> addTagForArticle(@RequestBody ArticleTagRelationDTO dto){
        return Result.status(articleService.addTagForArticle(dto));
    }

    @ApiOperation(value = "删除文章标签")
    @DeleteMapping("/tags")
    public Result<Boolean> deletedTagForArticle(@RequestBody ArticleTagRelationDTO dto){
        return Result.status(articleService.deletedTagForArticle(dto));
    }

//    //TODO：分类改写
//    @ApiOperation(value = "为文章添加分类")
//    @PostMapping("/category")
//    public Result<Boolean> addCategoryForArticle(@RequestBody ArticleCategoryRelationDTO dto){
//        return Result.status(articleService.addCategoryForArticle(dto));
//    }


    //TODO：分类改写start 2021/3/22-------------------------------------------------------------------------------

//    @ApiIgnore
//    @ApiOperation(value = "为文章添加或修改分类",notes = "原文章有分类则替换")
//    @PostMapping("/category/{article-id}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "article-id", value = "文章ID",required = true,
//                    paramType = "path", dataType = "String", dataTypeClass = String.class),
//            @ApiImplicitParam(name = "category-id", value = "分类ID",required = true,
//                    paramType = "query", dataType = "String", dataTypeClass = String.class)
//    })
//    public Result<Boolean> addCategoryForArticle(
//            @PathVariable("article-id") String articleId,
//            @RequestParam("category-id") String categoryId
//    ){
//        return Result.status(articleService.addCategoryForArticle(articleId, categoryId));
//    }

    @ApiOperation(value = "为文章添加或修改分类",notes = "新分类传递分类名称，已有分类传递分类ID")
    @PostMapping("/category/{article-id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article-id", value = "文章ID",required = true,
                    paramType = "path", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "category", value = "分类ID",required = true,
                    paramType = "query", dataType = "String", dataTypeClass = String.class)
    })
    public Result<Boolean> addCategoryForArticle(
            @PathVariable("article-id") String articleId,
            @RequestParam("category") String category
    ){
        return Result.status(articleService.addCategoryForArticle2(articleId, category));
    }


//    @ApiOperation(value = "删除文章分类")
//    @DeleteMapping("/category")
//    public Result<Boolean> deletedCategoryForArticle(@RequestBody ArticleCategoryRelationDTO dto){
//        return Result.status(articleService.deletedCategoryForArticle(dto));
//    }

    @ApiOperation(value = "删除文章分类")
    @DeleteMapping("/category/{article-id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article-id", value = "文章ID",required = true,
                    paramType = "path", dataType = "String", dataTypeClass = String.class),
    })
    public Result<Boolean> deletedCategoryForArticle(@PathVariable("article-id") String articleId){
        return Result.status(articleService.deletedCategoryForArticle(articleId));
    }

    // TODO: 分类改写
    @ApiIgnore
    @ApiOperation(value = "删除文章")
    @DeleteMapping("/article")
    @ApiImplicitParam(name = "ids[]",
            value = "id集合",
            required = true,
            paramType = "query",
            dataTypeClass = String.class,
            dataType = "String")
    public Result<Boolean> deletedArticle(@RequestParam("ids") List<String> ids){
        return Result.status(articleService.deletedArticle(ids));
    }


    @ApiOperation(value = "获取文章详细", notes = "文章body、分类、标签")
    @GetMapping("/detail/{id}")
    @ApiImplicitParam(name = "id",
            value = "文章ID",
            required = true,
            paramType = "path",
            dataTypeClass = String.class,
            dataType = "String")
    public Result<ArticleDetailVO> getArticleDetail(@PathVariable("id") String articleId){
        return Result.success(articleService.getDetail(articleId));
    }

    //TODO：分类改写end 2021/3/22-------------------------------------------------------------------------------

    @ApiOperation(value = "个人文章信息", notes = "文章列表")
    @GetMapping("/personal-list")
    public Result<IPage<Article>> personalArticleList(BasePageDTO dto){
        return Result.success(articleService.personalArticleList(dto));
    }

}
