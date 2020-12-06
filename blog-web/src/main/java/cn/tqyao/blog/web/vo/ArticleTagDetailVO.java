package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章标签
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ArticleTagDetailVO", description="文章-标签详细VO")
public class ArticleTagDetailVO extends ArticleTag {


}
