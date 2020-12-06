package cn.tqyao.blog.web.service;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.web.dto.ArticleDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 文章列表 服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
public interface IArticleService extends IService<Article> {


    Boolean addArticle(ArticleDTO dto);
}
