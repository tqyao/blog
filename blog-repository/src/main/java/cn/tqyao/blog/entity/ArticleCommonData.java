package cn.tqyao.blog.entity;

import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章点赞，收藏，关注
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleCommonData对象", description="文章点赞，收藏，关注")
public class ArticleCommonData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private String memberId;

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "操作类型:0 -> 点赞；1 -> 收藏")
    private Integer type;


}
