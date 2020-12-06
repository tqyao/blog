package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.entity.ArticleCommonData;
import cn.tqyao.blog.web.mapper.ArticleCommonDataMapper;
import cn.tqyao.blog.web.service.IArticleCommonDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章点赞，收藏，关注 服务实现类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
@Service
public class ArticleCommonDataServiceImpl extends ServiceImpl<ArticleCommonDataMapper, ArticleCommonData> implements IArticleCommonDataService {

}
