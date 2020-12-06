/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/3 9:25 <br>
 */
@Data
public class BasePageDTO implements Serializable {

    @ApiModelProperty("当前页")
    private Long current;

    @ApiModelProperty(value = "每页条数")
    private Long size;
}
