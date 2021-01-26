/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.vo;

import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.ArticleTag;
import cn.tqyao.blog.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/18 12:00 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleTagDetailVO", description="文章-标签详细VO")
public class HomeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Member member;

    private List<ArticleBaseDetailVO> articleList;

    private List<ArticleTag> hotTagList;

    private List<Article> hotArticle;

}
