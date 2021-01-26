package cn.tqyao.blog.web.mapper;

import cn.tqyao.blog.dao.ArticleDao;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.web.dto.ArticleBaseDetailConditionDTO;
import cn.tqyao.blog.web.vo.ArticleDetailVO;
import cn.tqyao.blog.web.vo.ArticleBaseDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 文章列表 Mapper 接口
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Repository
public interface ArticleMapper extends ArticleDao {

    ArticleDetailVO getDetail(String articleId);

//    List<ArticleBaseDetailVO> selectArticleBaseDetail(@Param("articleIds") List<String> articleIds);

    IPage<ArticleBaseDetailVO> selectArticleBaseDetail(@Param("page") Page page, @Param("articleIds") List<String> articleIds);

    IPage<ArticleBaseDetailVO> selectArticleBaseDetailConditionPage(@Param("page") Page<Article> page,
                                                                    @Param("condition") ArticleBaseDetailConditionDTO condition);


}
