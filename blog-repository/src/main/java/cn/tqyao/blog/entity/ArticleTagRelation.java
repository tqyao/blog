/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */

package cn.tqyao.blog.entity;

import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="文章列表")
public class ArticleTagRelation extends BaseEntity {

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "标签ID")
    private String tagId;

}
