package cn.tqyao.blog.web.mapper;

import cn.tqyao.blog.dao.ArticleDao;
import cn.tqyao.blog.entity.Article;

import cn.tqyao.blog.web.vo.ArticleDetailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

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
}
