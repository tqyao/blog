package cn.tqyao.blog.entity;

import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleTagRelation对象", description="")
public class ArticleTagRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "标签ID")
    private String tagId;


}
