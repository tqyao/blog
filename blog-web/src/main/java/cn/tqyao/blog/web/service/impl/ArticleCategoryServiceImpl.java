package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.ArticleCategory;
import cn.tqyao.blog.web.mapper.ArticleCategoryMapper;
import cn.tqyao.blog.web.service.IArticleCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {

}
