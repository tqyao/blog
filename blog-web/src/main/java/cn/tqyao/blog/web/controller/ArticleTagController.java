package cn.tqyao.blog.web.controller;


import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.service.IArticleTagService;
import cn.tqyao.blog.web.vo.TagDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章标签 前端控制器
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Api(tags = "文章-标签")
@RestController
@RequestMapping("/article-tags")
public class ArticleTagController {

    @Autowired
    private IArticleTagService articleTagService;

    @ApiOperation(value = "新增标签")
    @PostMapping
    public Result<Boolean> add(@RequestBody ArticleTagDTO dto) {
        return Result.status(articleTagService.addTag(dto));
    }

    @ApiOperation(value = "修改标签内容")
    @PutMapping("/{tag-id}")
    public Result<Boolean> update(@PathVariable("tag-id") String id, @RequestBody ArticleTagDTO dto) {
        return Result.status(articleTagService.updateTag(id, dto));
    }

    @ApiOperation(value = "查看所有标签")
    @GetMapping("/all")
    public Result<IPage<ArticleTag>> list(BasePageDTO page) {
        return Result.success(articleTagService.listTag(page));
    }

    @ApiOperation(value = "查询标签内容", notes = "通过标签ID查看标签内容")
    @GetMapping("/{tag-id}")
    public Result<ArticleTag> getOne(@PathVariable("tag-id") String tagId) {
        return Result.success(articleTagService.getById(tagId));
    }

    @ApiOperation(value = "查看标签详情")
    @GetMapping("/detail/{tag-id}")
    public Result<TagDetailVO> getDetail(@PathVariable("tag-id") String tagId) {
        return Result.success(articleTagService.getTagDetail(tagId));
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/{tag-id}")
    public Result<Boolean> deleted(@PathVariable("tag-id") String tagId) {
        return Result.status(articleTagService.removeById(tagId));
    }

}
