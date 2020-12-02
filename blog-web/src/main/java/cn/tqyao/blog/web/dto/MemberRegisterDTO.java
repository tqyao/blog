/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/20 16:08 <br>
 */
@Data
@ApiModel(value = "MemberRegisterDTO", description = "")
public class MemberRegisterDTO {

    @ApiModelProperty("用户名")
    @NotBlank(message = "username 不能为空")
    @Length(min = 8, message = "用户名至少8位")
    private String username;

    @ApiModelProperty("密码")
    @NotBlank(message = "password 不能为空")
    @Length(min = 12, message = "密码至少12位")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("验证码")
    private String authCode;

//    @ApiModelProperty("昵称")
//    private String nickname;
//
//    @ApiModelProperty("头像")
//    private String avatar;
//
//    @ApiModelProperty("性别：0 -> 男;1 -> 女")
//    private Integer gender;
//
//    @ApiModelProperty("生日")
//    private Date birthday;
}
