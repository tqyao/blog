package cn.tqyao.blog.common.result;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    FORBIDDEN(403, "没有相关权限"),


    USER_LOCKED_ERROR(3001,"用户被锁定"),
    USER_CREDENTIALS_EXPIRE_ERROR(3002,"密码过期"),
    USER_ACCOUNT_EXPIRED_ERROR(3003,"账户过期"),
    USER_DISABLED_ERROR(3004,"账户被禁用"),
    USER_BAD_CREDENTIALS_ERROR(3005,"用户名或者密码输入错误"),

    UNAUTHORIZED(401, "授权过期，请尝试刷新"),
    TOKEN_PARSE_ERROR(4000,"token解析错误,请确保使用正确的token格式"),
    TOKEN_TYPE_ERROR(4001,"token类型错误,请更换合适类型"),
    TOKEN_AUTHORIZED_FAIL_ERROR(4002,"token认证失败,请重新登录"),
    TOKEN_INVALIDATION_ERROR(4003,"无效token，请重新登录"),
    TOKEN_HEAD_ERROR(4004,"token空载体认证令牌"),

    SYSTEM_INNER_ERROR(5001,"系统内部出错");
    private long code;
    private String msg;

    ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
