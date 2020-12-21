package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.web.dto.ArticleCommonDataDTO;
import cn.tqyao.blog.web.service.IArticleCommonDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章点赞，收藏，关注 前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Api(tags = "文章-点赞、收藏管理")
@RestController
@RequestMapping("/article-common-data")
public class ArticleCommonDataController {

    @Autowired
    private IArticleCommonDataService articleCommonDataService;

    @ApiOperation(value = "添加用户文章、点赞收藏关系",notes = "用户点赞、收藏")
    @PostMapping("/common-data")
    public Result<Boolean> add(ArticleCommonDataDTO dto){
        return Result.status(articleCommonDataService.addCommonData(dto));
    }

    @ApiOperation(value = "删除用户点赞、收藏关系", notes = "用户取消点赞、收藏")
    @DeleteMapping("/common-data")
    public Result<Boolean> cancel(ArticleCommonDataDTO dto){
        return Result.status(articleCommonDataService.cancleCommonData(dto));
    }

//    public Result<>
}
