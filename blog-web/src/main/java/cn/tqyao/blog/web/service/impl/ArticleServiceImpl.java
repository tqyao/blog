package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.entity.*;
import cn.tqyao.blog.web.dto.*;
import cn.tqyao.blog.web.mapper.ArticleMapper;
import cn.tqyao.blog.web.service.*;
import cn.tqyao.blog.web.util.PageUtil;
import cn.tqyao.blog.web.vo.ArticleDetailVO;
import cn.tqyao.blog.web.vo.ArticleBaseDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
                            //TODO 查询标签是否存在

                            ArticleTagRelation articleTagRelation = new ArticleTagRelation();
                            articleTagRelation.setArticleId(articleId).setTagId(tagId);
                            return articleTagRelation;
                        }).collect(Collectors.toList()))
                .ifPresent(list -> articleTagRelationService.saveBatch(list));

        //添加文章类目关系
        Optional.ofNullable(dto.getCategorySet())
                .map(set -> set.stream()
                        .map(categoryId -> {
                            //TODO 查询文章分类是否存在

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
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateWeight(String articleId, Integer weight) {
        Article article = new Article();
        article.setWeight(weight);
        article.setId(articleId);
        return updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDraft(String articleId, Integer draft) {
        Article article = new Article();
        article.setDraft(draft);
        article.setId(articleId);
        return updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTagForArticle(ArticleTagRelationDTO dto) {

        Article article = Optional.ofNullable(getById(dto.getArticleId())).orElseThrow(() -> new CommonException("文章不存在"));

        List<ArticleTagRelation> articleTagRelationList = new ArrayList<>();
        Optional.ofNullable(dto.getTagSet())
                .orElse(new HashSet<>()).forEach(tagId -> {
            //TODO 查询标签是否存在

            ArticleTagRelation articleTagRelation = new ArticleTagRelation();
            articleTagRelation
                    .setArticleId(article.getId())
                    .setTagId(tagId);
            articleTagRelationList.add(articleTagRelation);
        });
        return articleTagRelationService.saveBatch(articleTagRelationList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deletedTagForArticle(ArticleTagRelationDTO dto) {
        Article article = Optional.ofNullable(getById(dto.getArticleId())).orElseThrow(() -> new CommonException("文章不存在"));

        List<String> relationList = Optional.ofNullable(dto.getTagSet())
                .map(tagSet -> tagSet.stream()
                        .map(tagId -> Optional.ofNullable(articleTagRelationService.getOne(Wrappers.<ArticleTagRelation>lambdaQuery()
                                .eq(ArticleTagRelation::getArticleId, article.getId())
                                .eq(ArticleTagRelation::getTagId, tagId)))
                                .orElseThrow(() -> new CommonException("文章含有未被添加的标签")).getId())
                        .collect(Collectors.toList())).orElse(new ArrayList<>());

        return articleTagRelationService.removeByIds(relationList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCategoryForArticle(ArticleCategoryRelationDTO dto) {
        Article article = Optional.ofNullable(getById(dto.getArticleId())).orElseThrow(() -> new CommonException("文章不存在"));

        List<ArticleCategoryRelation> list = new ArrayList<>();
        Optional.ofNullable(dto.getCategorySet())
                .orElse(new HashSet<>()).forEach(categoryId -> {
            //TODO 查询分类是否存在

            ArticleCategoryRelation articleCategoryRelation = new ArticleCategoryRelation();
            articleCategoryRelation
                    .setArticleId(article.getId())
                    .setCategoryId(categoryId);
            list.add(articleCategoryRelation);
        });
        return articleCategoryRelationService.saveBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deletedCategoryForArticle(ArticleCategoryRelationDTO dto) {
        Article article = Optional.ofNullable(getById(dto.getArticleId())).orElseThrow(() -> new CommonException("文章不存在"));

        List<String> categoryIds = dto.getCategorySet().stream()
                .map(categoryId -> Optional.ofNullable(articleCategoryRelationService
                        .getOne(Wrappers.<ArticleCategoryRelation>lambdaQuery()
                                .eq(ArticleCategoryRelation::getArticleId, article.getId())
                                .eq(ArticleCategoryRelation::getCategoryId, categoryId)))
                        .orElseThrow(() -> new CommonException("文章含有未被添加的分类")).getId())
                .collect(Collectors.toList());

        return articleCategoryRelationService.removeByIds(categoryIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deletedArticle(List<String> ids) {

        List<String> bodyIdList = new ArrayList<>();
        List<String> tagIdList = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();

        ids.forEach(articleId -> {
            Article article = Optional.ofNullable(getById(Wrappers.<Article>lambdaQuery().eq(Article::getId, articleId)))
                    .orElseThrow(() -> new CommonException("文章不存在"));

            bodyIdList.add(article.getBodyId());

            Optional.ofNullable(articleTagRelationService.list(Wrappers.<ArticleTagRelation>lambdaQuery()
                    .eq(ArticleTagRelation::getArticleId, articleId)))
                    .ifPresent(articleTagRelationList -> articleTagRelationList.forEach(relation -> {
                        tagIdList.add(relation.getTagId());
                    }));

            Optional.ofNullable(articleCategoryRelationService.list(Wrappers.<ArticleCategoryRelation>lambdaQuery()
                    .eq(ArticleCategoryRelation::getArticleId, articleId)))
                    .ifPresent(articleCategoryRelationList -> articleCategoryRelationList.forEach(relation -> {
                        categoryList.add(relation.getCategoryId());
                    }));
        });

        articleBodyService.removeByIds(bodyIdList);
        articleTagRelationService.removeByIds(tagIdList);
        articleCategoryRelationService.removeByIds(categoryList);
        removeByIds(ids);
        return true;
    }

    @Override
    public IPage<Article> homeList(BasePageDTO dto) {
        Page<Article> page = PageUtil.getPage(dto);
        return page(page, Wrappers.<Article>lambdaQuery().orderByDesc(Article::getCreateTime));
    }

    @Override
    public IPage<Article> personalArticleList(BasePageDTO dto) {
        Page<Article> page = PageUtil.getPage(dto);
        Member currentMember = memberService.getCurrentMember();

        return page(page, Wrappers.<Article>lambdaQuery()
                .eq(Article::getAuthorId, currentMember.getId())
                .orderByDesc(Article::getCreateTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleDetailVO getDetail(String articleId) {

        Article article = Optional.ofNullable(getById(articleId)).orElseThrow(() -> new CommonException("文章不存在"));
        // 文章观看数+1
        Article insertArticle = new Article();
        insertArticle.setViewCount(article.getViewCount()+1);
        insertArticle.setId(article.getId());
        updateById(insertArticle);
        return baseMapper.getDetail(articleId);
    }

    @Override
    public List<ArticleBaseDetailVO> getArticleBaseDetail(List<String> articleIds) {
        return baseMapper.selectArticleBaseDetail(articleIds);
    }


}
