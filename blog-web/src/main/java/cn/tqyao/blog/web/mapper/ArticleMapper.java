package cn.tqyao.blog.web.mapper;

import cn.tqyao.blog.dao.ArticleDao;

import cn.tqyao.blog.web.vo.ArticleDetailVO;
import cn.tqyao.blog.web.vo.ArticleBaseDetailVO;
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

    List<ArticleBaseDetailVO> selectArticleBaseDetail(@Param("articleIds") List<String> articleIds);
}
