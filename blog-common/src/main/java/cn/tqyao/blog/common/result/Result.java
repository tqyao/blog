package cn.tqyao.blog.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private long code;
    private String msg;
    private T data;


    /**
     * 成功返回结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        ResultCode rc = ResultCode.SUCCESS;
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            rc = ResultCode.FAILED;
        }
        return result(rc, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return result(ResultCode.SUCCESS.getCode(),msg, data);
    }


    /**
     * 失败返回结果
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(){
        return result(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMsg(),null);
    }

    /**
     * 失败返回结果
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg){
        return result(ResultCode.FAILED.getCode(), msg,null);
    }

    /**
     * 布尔参数返回结果
     * @param status
     * @param <T>
     * @return
     */
    public static <T> Result<T> status(boolean status) {
        if (status) {
            return success();
        } else {
            return error();
        }
    }

    /**
     * 返回已知类型结果
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> custom(IResultCode resultCode) {
        return result(resultCode.getCode(), resultCode.getMsg(), null);
    }

    /**
     * 返回已知类型结果
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> custom(IResultCode resultCode, T error) {
        return result(resultCode.getCode(), resultCode.getMsg(), error);
    }

    /**
     * 私有构造已知返回结果
     * @param resultCode
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Result<T> result(IResultCode resultCode, T data) {
        return result(resultCode.getCode(), resultCode.getMsg(),data);
    }

    /**
     * 私有构造返回结果
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Result<T> result(long code, String msg, T data) {
        return new Result<>(code, msg, data);
    }
}
