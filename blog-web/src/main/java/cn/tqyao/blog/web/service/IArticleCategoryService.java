package cn.tqyao.blog.web.service;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.web.dto.ArticleCategoryDTO;
import cn.tqyao.blog.web.vo.CategoryArticleDetailVO;
import cn.tqyao.blog.web.vo.CategoryDetailVO2;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章分类 服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
public interface IArticleCategoryService extends IService<ArticleCategory> {

    Boolean add(ArticleCategoryDTO dto);

    Boolean updateCategory(String id, ArticleCategoryDTO dto);

    IPage<ArticleCategory> listCategory(BasePageDTO dto);

    CategoryArticleDetailVO getCategoryArticleDetail(BasePageDTO dto, String categoryId);

    CategoryDetailVO2 getCategoryArticleDetails(BasePageDTO dto, String categoryId);
}
