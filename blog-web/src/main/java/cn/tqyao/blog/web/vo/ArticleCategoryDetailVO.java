package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.entity.ArticleTag;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="ArticleCategoryDetailVO", description="文章-分类详细VO")
public class ArticleCategoryDetailVO extends ArticleCategory {


}
