package cn.tqyao.blog.web.service;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.web.dto.*;
import cn.tqyao.blog.web.vo.ArticleDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 文章列表 服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
public interface IArticleService extends IService<Article> {


    Boolean addArticle(ArticleDTO dto);

    Boolean updateBody(String id, ArticleBodyDTO dto);

    Boolean updateBase(String id, ArticleUpdateBaseDTO dto);

    Boolean updateWeight(String articleId, Integer weight);

    Boolean updateDraft(String articleId, Integer draft);

    Boolean addTagForArticle(ArticleTagRelationDTO dto);

    Boolean deletedTagForArticle(ArticleTagRelationDTO dto);

    Boolean addCategoryForArticle(ArticleCategoryRelationDTO dto);

    Boolean deletedCategoryForArticle(ArticleCategoryRelationDTO dto);

    Boolean deletedArticle(List<String> ids);

    IPage<Article> homeList(BasePageDTO dto);

    IPage<Article> personalArticleList(BasePageDTO dto);

    ArticleDetailVO getDetail(String articleId);
}
