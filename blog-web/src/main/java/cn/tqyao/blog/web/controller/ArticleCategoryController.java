package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.web.dto.ArticleCategoryDTO;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import cn.tqyao.blog.web.vo.ArticleCategoryDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@RequestMapping("/article-categories")
public class ArticleCategoryController {

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @ApiOperation(value = "添加分类")
    @PostMapping("/category")
    public Result<Boolean> add(@RequestBody ArticleCategoryDTO dto){
        return Result.success(articleCategoryService.add(dto));
    }

    @ApiOperation(value = "修改分类")
    @PutMapping("/category/{id}")
    @ApiImplicitParam(value = "类目ID",
            name = "id", required = true,
            paramType = "path",
            dataTypeClass = String.class,
            dataType = "String"
    )
    public Result<Boolean> update(@PathVariable String id, @RequestBody ArticleCategoryDTO dto){
        return Result.status(articleCategoryService.updateCategory(id, dto));
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping("/category/{id}")
    @ApiImplicitParam(value = "类目ID",
            name = "id",
            required = true,
            paramType = "path",
            dataTypeClass = String.class,
            dataType = "String"
    )
    public Result<Boolean> update(@PathVariable String id){
        return Result.status(articleCategoryService.removeById(id));
    }

    @ApiOperation(value = "查看所有分类")
    @GetMapping("/list")
    public Result<IPage<ArticleCategory>> list(BasePageDTO dto){
        return Result.success(articleCategoryService.listCategory(dto));
    }


    @ApiOperation(value = "查询分类信息")
    @GetMapping("/category/{id}")
    @ApiImplicitParam(name = "id", value = "类目ID", required = true,
            paramType = "path", dataType = "String", dataTypeClass = String.class)
    public Result<ArticleCategory> getOne(@PathVariable("id") String categoryId){
        return Result.success(articleCategoryService.getById(categoryId));
    }

    //TODO 分类详情
    @ApiIgnore
    public Result<ArticleCategoryDetailVO> getDetail(){
        return Result.success();
    }

}
