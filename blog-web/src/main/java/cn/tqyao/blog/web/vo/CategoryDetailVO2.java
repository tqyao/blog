package cn.tqyao.blog.web.vo;


import cn.tqyao.blog.entity.ArticleCategory;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
@ApiModel(value = "CategoryArticleDetailVO", description = "分类文章基本信息VO")
public class CategoryDetailVO2 extends ArticleCategory {

    @ApiModelProperty(value = "含该分类的文章细节")
    private IPage<ArticleBaseDetailVO2> articleBaseDetailVOIPage;

}
