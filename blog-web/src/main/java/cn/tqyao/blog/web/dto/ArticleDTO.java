/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/6 21:00 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleDTO", description="文章-DTO")
public class ArticleDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "内容ID")
    private String bodyId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "概要")
    private String summary;

    @ApiModelProperty(value = "权重：0 -> 普通；1 -> 置顶")
    private Integer weight;

    @ApiModelProperty(value = "是否草稿:0 -> 否；1 -> 是")
    private Integer draft;

    @ApiModelProperty(value = "文章内容")
    private ArticleBodyDTO articleBody;

    @ApiModelProperty(value = "文章标签")
    private Set<ArticleTagDTO> articleTagSet;

    @ApiModelProperty(value = "文章分类")
    private Set<ArticleCategoryDTO> articleCategorySet;

}
