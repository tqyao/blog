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
 * 后台管理-角色表
 * </p>
 *
 * @author tqyao
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="后台管理-角色表")
public class SysRole extends BaseEntity{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
