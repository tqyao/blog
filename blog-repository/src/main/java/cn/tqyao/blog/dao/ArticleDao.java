package cn.tqyao.blog.dao;

import cn.tqyao.blog.entity.Article;
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
public interface ArticleDao extends BaseMapper<Article> {

}
