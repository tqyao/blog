package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.*;
import cn.tqyao.blog.web.dto.ArticleDTO;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.mapper.ArticleMapper;
import cn.tqyao.blog.web.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章列表 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private IMemberService memberService;
    private IArticleCategoryRelationService articleCategoryRelationService;
    private IArticleTagRelationService articleTagRelationService;
    private IArticleBodyService articleBodyService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addArticle(ArticleDTO dto) {
        //获取登录用户
        Member currentMember = memberService.getCurrentMember();

        //插入文章内容
        ArticleBody body = new ArticleBody();
        BeanUtils.copyProperties(dto.getArticleBody(), body);
        articleBodyService.save(body);

        //插入文章
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        article
                .setViewCount(0)
                .setLikeCount(0)
                .setCollectCount(0)
                .setCommentCount(0)
                .setAuthorId(currentMember.getId());
        save(article);

        String articleId = article.getId();
        //添加文章标签关系
        Optional.ofNullable(dto.getTagSet())
                .map(set -> set.stream()
                        .map(tagId -> {
                            ArticleTagRelation articleTagRelation = new ArticleTagRelation();
                            articleTagRelation.setArticleId(articleId).setTagId(tagId);
                            return articleTagRelation;
                        }).collect(Collectors.toList()))
                .ifPresent(list -> articleTagRelationService.saveBatch(list));

        //添加文章类目关系
        Optional.ofNullable(dto.getCategorySet())
                .map(set -> set.stream()
                        .map(categoryId -> {
                            ArticleCategoryRelation articleCategoryRelation = new ArticleCategoryRelation();
                            articleCategoryRelation.setArticleId(articleId).setCategoryId(categoryId);
                            return articleCategoryRelation;
                        }).collect(Collectors.toList()))
                .ifPresent(list -> articleCategoryRelationService.saveBatch(list));

        return true;
    }
}
