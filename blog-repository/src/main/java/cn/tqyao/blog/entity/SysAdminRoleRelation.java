package cn.tqyao.blog.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 后台管理-用户角色关系表
 * </p>
 *
 * @author tqyao
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_admin_role_relation")
@ApiModel(value="SysAdminRoleRelation对象", description="后台管理-用户角色关系表")
public class SysAdminRoleRelation extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private String adminId;

    private String roleId;

}
