package cn.tqyao.blog.web.service;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.vo.TagDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

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

    IPage<ArticleTag> listTag(BasePageDTO pageDTO);

    TagDetailVO getTagDetail(String tagId, BasePageDTO dto);
}
