package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.common.util.PageUtil;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.entity.ArticleCategoryRelation;
import cn.tqyao.blog.web.dto.ArticleCategoryDTO;
import cn.tqyao.blog.web.mapper.ArticleCategoryMapper;
import cn.tqyao.blog.web.service.IArticleCategoryRelationService;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.vo.ArticleBaseDetailVO;
import cn.tqyao.blog.web.vo.ArticleBaseDetailVO2;
import cn.tqyao.blog.web.vo.CategoryArticleDetailVO;
import cn.tqyao.blog.web.vo.CategoryDetailVO2;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IArticleCategoryRelationService articleCategoryRelationService;
    @Autowired
    private IArticleService articleService;

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

    @Override
    public CategoryArticleDetailVO getCategoryArticleDetail(BasePageDTO dto, String categoryId) {
        CategoryArticleDetailVO vo = new CategoryArticleDetailVO();

        ArticleCategory category = Optional.ofNullable(getById(categoryId)).orElseThrow(() -> new CommonException("分类不存在"));
        BeanUtils.copyProperties(category, vo);

        IPage<ArticleBaseDetailVO> baseVOList = Optional.ofNullable(articleCategoryRelationService
                .list(Wrappers.<ArticleCategoryRelation>lambdaQuery()
                        .eq(ArticleCategoryRelation::getCategoryId, category.getId())))
                .map(relations -> relations.stream()
                        .map(ArticleCategoryRelation::getArticleId).collect(Collectors.toList()))
                .map(articleIds -> articleService.getArticleBaseDetail(dto,articleIds)).orElse(new Page<>());

        vo.setArticleBaseDetailVOList(baseVOList);

        return vo;
    }

    @Override
    public CategoryDetailVO2 getCategoryArticleDetails(BasePageDTO dto, String categoryId) {
        CategoryDetailVO2 vo = new CategoryDetailVO2 ();

        ArticleCategory articleCategory = Optional.ofNullable (getById (categoryId)).orElseThrow (() -> new CommonException ("文章分类不存在"));
        BeanUtils.copyProperties (articleCategory, vo);

        IPage<ArticleBaseDetailVO2> baseVOList = Optional.ofNullable (articleCategoryRelationService
                .list (Wrappers.<ArticleCategoryRelation>lambdaQuery ()
                        .eq (ArticleCategoryRelation::getCategoryId, categoryId)))
                .map (relations -> relations.stream ()
                        .map (ArticleCategoryRelation::getArticleId).collect (Collectors.toList ()))
                .map (articleId -> articleService.getArticleBaseDetail2 (dto, articleId)).orElse (new Page<> ());

        vo.setArticleBaseDetailVOIPage (baseVOList);
        return vo;
    }

}
