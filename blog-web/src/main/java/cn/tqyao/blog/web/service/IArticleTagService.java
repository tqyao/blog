package cn.tqyao.blog.web.service;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.vo.ArticleTagDetailVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章标签 服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
public interface IArticleTagService extends IService<ArticleTag> {

    Boolean addTag(ArticleTagDTO dto);

    Boolean updateTag(String id, ArticleTagDTO dto);

    Page<ArticleTag> listTag(BasePageDTO pageDTO);

    ArticleTagDetailVO getDetailById(String tagId);
}
