/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.exception;

import cn.tqyao.blog.common.result.IResultCode;
import lombok.Getter;

/**
 * .<br>
 * 业务公用异常
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/5 11:14 <br>
 */
@Getter
public class CommonException extends RuntimeException {

    private IResultCode resultCode;

    public CommonException(IResultCode resultCode){
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public CommonException(String message){
        super(message);
    }
    public CommonException(String message, Throwable cause){
        super(message, cause);
    }
    public CommonException(Throwable cause) {
        super(cause);
    }
}
