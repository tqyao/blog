package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.web.dto.ArticleCategoryDTO;
import cn.tqyao.blog.web.mapper.ArticleCategoryMapper;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import cn.tqyao.blog.web.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(ArticleCategoryDTO dto) {
        ArticleCategory category = new ArticleCategory();
        BeanUtils.copyProperties(dto,category);
        return save(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCategory(String id, ArticleCategoryDTO dto) {
        ArticleCategory category = new ArticleCategory();
        BeanUtils.copyProperties(dto,category);
        category.setId(id);
        return updateById(category);
    }

    @Override
    public IPage<ArticleCategory> listCategory(BasePageDTO dto) {
        return page(PageUtil.getPage(dto));
    }

}
