package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.web.mapper.ArticleMapper;
import cn.tqyao.blog.web.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
