package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.web.dto.ArticleDTO;
import cn.tqyao.blog.web.dto.MemberRegisterDTO;
import cn.tqyao.blog.web.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public Result add(@RequestBody ArticleDTO dto){
        return Result.status(articleService.addArticle(dto));
    }



}
