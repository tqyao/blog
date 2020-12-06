package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.mapper.ArticleTagMapper;
import cn.tqyao.blog.web.service.IArticleTagService;
import cn.tqyao.blog.web.util.PageUtil;
import cn.tqyao.blog.web.vo.ArticleTagDetailVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.lettuce.core.dynamic.support.GenericTypeResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.TagElement;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {

    @Override
    public Boolean addTag(ArticleTagDTO dto) {
        ArticleTag tag = new ArticleTag();
        BeanUtils.copyProperties(dto, tag);
        return save(tag);
    }

    @Override
    public Boolean updateTag(String id, ArticleTagDTO dto) {
        ArticleTag tag = new ArticleTag();
        BeanUtils.copyProperties(dto, tag);
        tag.setId(id);
        return updateById(tag);
    }

    @Override
    public Page<ArticleTag> listTag(BasePageDTO pageDTO) {
        return page(PageUtil.getPage(pageDTO));
    }

    @Override
    public ArticleTagDetailVO getDetailById(String tagId) {
        return null;
    }
}
