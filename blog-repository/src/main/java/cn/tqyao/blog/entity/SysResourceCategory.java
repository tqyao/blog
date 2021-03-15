package cn.tqyao.blog.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import cn.tqyao.blog.base.BaseEntity;
import java.sql.Blob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 *
 * </p>
 *
 * @author tqyao
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_resource_category")
@ApiModel(value="SysResourceCategory对象", description="")
public class SysResourceCategory extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源分类名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
