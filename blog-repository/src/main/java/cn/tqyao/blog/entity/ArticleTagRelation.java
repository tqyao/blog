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

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/6 21:18 <br>
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="文章列表")
public class ArticleTagRelation extends BaseEntity {

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "分类ID")
    private String categoryId;
}
