package cn.tqyao.blog.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="ArticleCategoryRelationDTO", description="文章-分类-关系-DTO")
public class ArticleCategoryRelationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章ID",example = "UUID")
    private String articleId;

    @ApiModelProperty(value = "分类ID集合")
    private Set<String> categorySet;

//    @ApiModelProperty(value = "分类ID")
//    private String categoryId;
}
