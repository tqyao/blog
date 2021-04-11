package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleTag;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
@ApiModel(value="TagDetailVO", description="标签文章详细VO")
public class TagDetailVO extends ArticleTag {

//    @ApiModelProperty(value = "含标签的文章细节")
//    List<ArticleBaseDetailVO> articleBaseDetailVOList;

//    @ApiModelProperty(value = "含标签的文章细节分页")
//    private IPage<ArticleBaseDetailVO> ArticleBaseDetailVOPage;

    private IPage<Article> articlePage;
}
