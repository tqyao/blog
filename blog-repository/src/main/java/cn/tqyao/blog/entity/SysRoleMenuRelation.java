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
 * 后台管理-角色菜单关系表
 * </p>
 *
 * @author tqyao
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu_relation")
@ApiModel(value="SysRoleMenuRelation对象", description="后台管理-角色菜单关系表")
public class SysRoleMenuRelation extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "菜单ID")
    private String menuId;

}
