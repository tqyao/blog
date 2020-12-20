package cn.tqyao.blog.web.dto;

import cn.tqyao.blog.enums.ArticleCommonDataEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 文章点赞，收藏，关注
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleCommonData对象", description="文章点赞，收藏")
public class ArticleCommonDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID")
    private String articleId;

    @ApiModelProperty(value = "操作类型:0 -> 点赞；1 -> 收藏")
    private ArticleCommonDataEnum type;

}
