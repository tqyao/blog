package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleCommonData;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.enums.ArticleCommonDataEnum;
import cn.tqyao.blog.web.dto.ArticleCommonDataDTO;
import cn.tqyao.blog.web.mapper.ArticleCommonDataMapper;
import cn.tqyao.blog.web.service.IArticleCommonDataService;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 文章点赞，收藏 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleCommonDataServiceImpl extends ServiceImpl<ArticleCommonDataMapper, ArticleCommonData> implements IArticleCommonDataService {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IArticleService articleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCommonData(ArticleCommonDataDTO dto) {

        Member currentMember = memberService.getCurrentMember();

        Article article = Optional.ofNullable(articleService.getById(dto.getArticleId()))
                .orElseThrow(() -> new CommonException("文章不存在"));

        //文章的点赞或收藏数+1
        Article update = new Article();
        update.setId(article.getId());

        if (Objects.equals(ArticleCommonDataEnum.LIVE_TYPE, dto.getType())) {
            update.setLikeCount(article.getLikeCount() + 1);
        } else if (Objects.equals(ArticleCommonDataEnum.COLLECTION_TYPE, dto.getType())) {
            update.setCollectCount(article.getCollectCount() + 1);
        }
        articleService.updateById(update);

        ArticleCommonData commonData = new ArticleCommonData();
        commonData
                .setMemberId(currentMember.getId())
                .setArticleId(article.getId())
                .setType(0);
        return save(commonData);
    }

    @Override
    public Boolean cancleCommonData(ArticleCommonDataDTO dto) {
        Member currentMember = memberService.getCurrentMember();

        Article article = Optional.ofNullable(articleService.getById(dto.getArticleId()))
                .orElseThrow(() -> new CommonException("文章不存在"));

        //文章的点赞或收藏数-1
        Article update = new Article();
        update.setId(article.getId());

        if (Objects.equals(ArticleCommonDataEnum.LIVE_TYPE, dto.getType())) {
            update.setLikeCount(article.getLikeCount() - 1);
        } else if (Objects.equals(ArticleCommonDataEnum.COLLECTION_TYPE, dto.getType())) {
            update.setCollectCount(article.getCollectCount() - 1);
        }
        articleService.updateById(update);

        return remove(Wrappers.<ArticleCommonData>lambdaQuery()
                .eq(ArticleCommonData::getMemberId, currentMember.getId())
                .eq(ArticleCommonData::getArticleId, article.getId())
                .eq(ArticleCommonData::getType, dto.getType().getCode()));
    }
}
