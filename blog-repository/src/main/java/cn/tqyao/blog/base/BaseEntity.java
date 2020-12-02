/**
 * Copyright 2020-2030 Jinhui Technology Co. LTD  All Rights Reserved.
 */
package cn.tqyao.blog.base;


import cn.tqyao.blog.common.validated.group.UpdateValidated;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/5 11:26  <br>
 * @version 1.0.0 <br>
*/
@Data
@ApiModel(value = "BaseEntity", description = "实体公共字段")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "ID 不能为空", groups = UpdateValidated.class)
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id", example = "uuid")
    private String id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", example = "2020-01-01 12:00:00")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间", example = "2020-01-01 12:00:00")
    private Date updateTime;

    @ApiModelProperty(value = "删除标记,0表示没有被删除，1表示主动删除，2表示被动删除")
    @TableField(value = "deleted",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

}
