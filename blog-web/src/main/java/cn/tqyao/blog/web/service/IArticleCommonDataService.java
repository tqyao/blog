package cn.tqyao.blog.web.service;

import cn.tqyao.blog.entity.ArticleCommonData;
import cn.tqyao.blog.web.dto.ArticleCommonDataDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章点赞，收藏，关注 服务类
 * </p>
 *
 * @author -Tanqy
 * @since 2020-12-02
 */
public interface IArticleCommonDataService extends IService<ArticleCommonData> {

    Boolean addCommonData(ArticleCommonDataDTO dto);

    Boolean cancleCommonData(ArticleCommonDataDTO dto);
}
