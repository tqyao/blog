package cn.tqyao.blog.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tqyao.blog.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 后台管理-异常日志
 * </p>
 *
 * @author tqyao
 * @since 2021-04-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("log_exception")
@ApiModel(value="LogException对象", description="后台管理-异常日志")
public class LogException extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请求接口")
    private String uri;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private String param;

    @ApiModelProperty(value = "请求描述")
    private String description;

    @ApiModelProperty(value = "异常信息")
    private String error;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "ip来源")
    private String ipSource;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "用户代理")
    private String userAgent;

    public LogException(String uri, String method, String description, String error, String ip, String userAgent) {
        this.uri = uri;
        this.method = method;
        this.description = description;
        this.error = error;
        this.ip = ip;
        this.userAgent = userAgent;
    }

}
