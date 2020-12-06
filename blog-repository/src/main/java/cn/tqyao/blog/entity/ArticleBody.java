package cn.tqyao.blog.entity;

import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章内容
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleBody对象", description="文章内容")
public class ArticleBody extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章内容-html")
    private String contentHtml;


}
