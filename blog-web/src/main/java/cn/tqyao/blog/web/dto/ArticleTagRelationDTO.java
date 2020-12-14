package cn.tqyao.blog.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleTagRelationDTO", description="文章-标签-关系-DTO")
public class ArticleTagRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID",example = "UUID")
    private String articleId;

    @ApiModelProperty(value = "标签ID集合")
    private Set<String> tagSet;

}
