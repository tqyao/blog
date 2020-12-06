package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.web.dto.ArticleDTO;
import cn.tqyao.blog.web.mapper.ArticleMapper;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.service.IArticleTagService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章列表 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IArticleCategoryService articleCategoryService;
    @Autowired
    private IArticleTagService articleTagService;


    @Override
    public Boolean addArticle(ArticleDTO dto) {

        return null;
    }
}
