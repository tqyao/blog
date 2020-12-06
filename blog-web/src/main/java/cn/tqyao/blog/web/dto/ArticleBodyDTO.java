/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/6 21:32 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleBodyDTO", description="文章内容-DTO")
public class ArticleBodyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章内容-html")
    private String contentHtml;
}
