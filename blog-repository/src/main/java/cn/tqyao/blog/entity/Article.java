package cn.tqyao.blog.entity;

import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章列表
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Article对象", description="文章列表")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "内容ID")
    private String bodyId;

    @ApiModelProperty(value = "作者ID")
    private String authorId;

    @ApiModelProperty(value = "作者ID")
    private String categoryId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "概要")
    private String summary;

    @ApiModelProperty(value = "观看数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer likeCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "收藏数")
    private Integer collectCount;

    @ApiModelProperty(value = "权重：0 -> 普通；1 -> 置顶")
    private Integer weight;

    @ApiModelProperty(value = "是否草稿:0 -> 否；1 -> 是")
    private Integer draft;


}
