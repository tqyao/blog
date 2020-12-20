package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.base.BasePageDTO;
import cn.tqyao.blog.common.exception.CommonException;
import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.entity.ArticleTagRelation;
import cn.tqyao.blog.web.dto.ArticleTagDTO;
import cn.tqyao.blog.web.mapper.ArticleTagMapper;
import cn.tqyao.blog.web.service.IArticleService;
import cn.tqyao.blog.web.service.IArticleTagRelationService;
import cn.tqyao.blog.web.service.IArticleTagService;
import cn.tqyao.blog.web.util.PageUtil;
import cn.tqyao.blog.web.vo.TagArticleDetailVO;
import cn.tqyao.blog.web.vo.TagDetailVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private IArticleTagRelationService articleTagRelationService;
    @Autowired
    private IArticleService articleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTag(ArticleTagDTO dto) {
        ArticleTag tag = new ArticleTag();
        BeanUtils.copyProperties(dto, tag);
        return save(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTag(String id, ArticleTagDTO dto) {
        ArticleTag tag = new ArticleTag();
        BeanUtils.copyProperties(dto, tag);
        tag.setId(id);
        return updateById(tag);
    }

    @Override
    public IPage<ArticleTag> listTag(BasePageDTO pageDTO) {
        return page(PageUtil.getPage(pageDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TagDetailVO getTagDetail(String tagId) {
        TagDetailVO vo = new TagDetailVO();

        ArticleTag tag = Optional.ofNullable(getById(tagId)).orElseThrow(() -> new CommonException("标签不存在"));

        List<String> articleIds = Optional.ofNullable(articleTagRelationService
                .list(Wrappers.<ArticleTagRelation>lambdaQuery()
                        .eq(ArticleTagRelation::getTagId, tag.getId())))
                .map(rList -> rList.stream().map(ArticleTagRelation::getArticleId).collect(Collectors.toList()))
                .orElse(new ArrayList<>());

        List<TagArticleDetailVO> detailVOS = null;
        if (!CollectionUtils.isEmpty(articleIds)) {
            detailVOS = articleService.getArticleBaseDetail(articleIds);
        }

        BeanUtils.copyProperties(tag, vo);
        vo.setTagArticleDetailVOList(detailVOS);
        return vo;
    }
}
