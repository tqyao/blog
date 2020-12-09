package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.entity.*;
import cn.tqyao.blog.web.dto.ArticleBodyDTO;
import cn.tqyao.blog.web.dto.ArticleDTO;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.dto.ArticleUpdateBaseDTO;
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
        ArticleBody insertBody = Optional.ofNullable(dto.getArticleBody()).map(bodyDTO -> {
            ArticleBody body = new ArticleBody();
            BeanUtils.copyProperties(bodyDTO, body);
            return body;
        }).orElse(new ArticleBody("", ""));

        articleBodyService.save(insertBody);

        //插入文章
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        article
                .setViewCount(0)
                .setLikeCount(0)
                .setCollectCount(0)
                .setCommentCount(0)
                .setAuthorId(currentMember.getId())
                .setBodyId(insertBody.getId());
        save(article);

        String articleId = article.getId();

        //添加文章标签关系
        Optional.ofNullable(dto.getTagSet())
                .map(set -> set.stream()
                        .map(tagId -> {
                            //查询标签是否存在

                            ArticleTagRelation articleTagRelation = new ArticleTagRelation();
                            articleTagRelation.setArticleId(articleId).setTagId(tagId);
                            return articleTagRelation;
                        }).collect(Collectors.toList()))
                .ifPresent(list -> articleTagRelationService.saveBatch(list));

        //添加文章类目关系
        Optional.ofNullable(dto.getCategorySet())
                .map(set -> set.stream()
                        .map(categoryId -> {
                            //查询文章分类是否存在

                            ArticleCategoryRelation articleCategoryRelation = new ArticleCategoryRelation();
                            articleCategoryRelation.setArticleId(articleId).setCategoryId(categoryId);
                            return articleCategoryRelation;
                        }).collect(Collectors.toList()))
                .ifPresent(list -> articleCategoryRelationService.saveBatch(list));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBody(String id, ArticleBodyDTO dto) {
        //用ID查article_body表
        ArticleBody body = Optional.ofNullable(articleBodyService.getById(id)).orElseGet(() -> {
            //如果article_body无记录，再用ID查article表
            return Optional.ofNullable(getById(id))
                    .map(article -> articleBodyService.getById(article.getBodyId()))
                    .orElseThrow(() -> new CommonException("文章体不存在"));
        });
        ArticleBody articleBody = new ArticleBody();
        BeanUtils.copyProperties(dto, articleBody);
        articleBody.setId(body.getId());

        return articleBodyService.updateById(articleBody);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBase(String id, ArticleUpdateBaseDTO dto) {
        Article article = new Article();
        BeanUtils.copyProperties(dto, article);
        article.setId(id);
        return updateById(article);
    }

    @Override
    public Boolean updateWeight(String articleId, Integer weight) {
        Article article = new Article();
        article.setWeight(weight);
        article.setId(articleId);
        return updateById(article);
    }

    @Override
    public Boolean updateDraft(String articleId, Integer draft) {
        Article article = new Article();
        article.setDraft(draft);
        article.setId(articleId);
        return updateById(article);
    }


}
