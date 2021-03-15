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
 * 后台管理-菜单表
 * </p>
 *
 * @author tqyao
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value="SysMenu对象", description="后台管理-菜单表")
public class SysMenu extends BaseEntity{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父级权限ID")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String title;

    @ApiModelProperty(value = "菜单级数")
    private String level;

    @ApiModelProperty(value = "菜单排序")
    private String sort;

    @ApiModelProperty(value = "前端隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "前端名称")
    private String name;

    @ApiModelProperty(value = "前端图标")
    private String icon;

}
