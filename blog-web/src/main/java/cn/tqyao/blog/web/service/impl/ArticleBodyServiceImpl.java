package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.ArticleBody;

import cn.tqyao.blog.web.mapper.ArticleBodyMapper;
import cn.tqyao.blog.web.service.IArticleBodyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleBodyServiceImpl extends ServiceImpl<ArticleBodyMapper, ArticleBody> implements IArticleBodyService {

}
