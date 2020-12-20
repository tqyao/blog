package cn.tqyao.blog.web.mapper;

import cn.tqyao.blog.dao.ArticleTagDao;
import cn.tqyao.blog.web.vo.TagDetailVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 文章标签 Mapper 接口
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Repository
public interface ArticleTagMapper extends ArticleTagDao {

    List<TagDetailVO> getTagDetailById(String tagId);
}
