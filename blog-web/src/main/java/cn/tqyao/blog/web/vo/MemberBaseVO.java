/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.vo;

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
 * @date Create in 2020/12/13 22:06 <br>
 */
@Data
@Accessors(chain = true)
@ApiModel(value="MemberBaseVO", description="用户基本信息VO")
public class MemberBaseVO implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private String memberId;

    @ApiModelProperty(value = "用户昵称")
    private String memberNickname;

    @ApiModelProperty(value = "用户头像")
    private String memberAvatar;


}
