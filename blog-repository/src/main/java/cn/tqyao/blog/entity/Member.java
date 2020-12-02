package cn.tqyao.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author -Tanqy
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("b_member")
@ApiModel(value="Member对象", description="")
public class Member extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, message = "用户名至少4位")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码至少6位")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "登录状态")
    private Integer loginStatus;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

}
