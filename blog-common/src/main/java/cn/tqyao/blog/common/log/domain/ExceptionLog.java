package cn.tqyao.blog.common.log.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * <p>
 * 后台管理-异常日志
 * </p>
 *
 * @Id ObjectId类型的自增ID
 * @Document(collation = "数据库表名")
 * @Field("字段名")
 *
 * @author tqyao
 * @since 2021-04-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value="LogException对象", description="后台管理-异常日志")
@Document(collation = "exceptionL_log")
public class ExceptionLog {

//    private static final long serialVersionUID = 1L;

    @MongoId
    @ApiModelProperty(value = "ID")
    private String id;

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


}
