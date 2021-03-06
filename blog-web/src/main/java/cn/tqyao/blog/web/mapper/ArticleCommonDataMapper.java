package cn.tqyao.blog.web.mapper;

import cn.tqyao.blog.dao.ArticleCommonDataDao;
import cn.tqyao.blog.entity.ArticleCommonData;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 文章点赞，收藏，关注 Mapper 接口
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Repository
public interface ArticleCommonDataMapper extends ArticleCommonDataDao {

}
