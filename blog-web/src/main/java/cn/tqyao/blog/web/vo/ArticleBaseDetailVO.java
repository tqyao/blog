/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.entity.ArticleTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/20 15:58 <br>
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ArticleBaseDetailVO", description="文章基本详情信息")
public class ArticleBaseDetailVO extends Article {

    @ApiModelProperty(value = "文章作者基本信息")
    private MemberBaseVO memberBaseVO;

    @ApiModelProperty(value = "文章标签集")
    private List<ArticleTag> tagList;

    @ApiModelProperty(value = "文章分类集")
    private List<ArticleCategory> categoryList;

}
