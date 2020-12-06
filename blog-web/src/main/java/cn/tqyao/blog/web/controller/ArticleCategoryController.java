package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章分类 前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Api(tags = "文章-类目")
@RestController
@RequestMapping("/article/category")
public class ArticleCategoryController {

//    @Autowired
//    private IArticleCategoryService articleCategoryService;
//
//    @ApiOperation(value = "添加分类")
//    public Result add(@RequestBody ArticleCategory category){
//        return Result.success();
//    }


}
