/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/4/11 15:49 <br>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {

    @ApiModelProperty(value = "操作描述")
    private String description;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "操作时间")
    private long starTime;

    @ApiModelProperty(value = "消耗时间")
    private Integer spendTime;

    @ApiModelProperty(value = "跟路径")
    private String basePath;

    @ApiModelProperty(value = "URI")
    private String uri;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "Ip 地址")
    private String ip;

    @ApiModelProperty(value = "请求参数")
    private Object parameter;

    @ApiModelProperty(value = "返回结果")
    private Object result;

}
