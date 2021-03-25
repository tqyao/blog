/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleBody;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.entity.ArticleTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/13 21:13 <br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleDetailVO", description="文章详情VO")
public class ArticleDetailVO extends Article {

    @ApiModelProperty(value = "文章作者基本信息")
    private MemberBaseVO memberBaseVO;

    @ApiModelProperty(value = "文章体")
    private ArticleBody articleBody;

    @ApiModelProperty(value = "文章标签集")
    private List<ArticleTag> tagList;

}
