/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.exception;

import cn.tqyao.blog.common.result.IResultCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;


/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/19 14:48 <br>
 */
public class TokenAuthenticationException extends AuthenticationException {

    //为空时不序列化
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Getter
    private String username;

    @Getter
    private IResultCode resultCode;

    public TokenAuthenticationException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public TokenAuthenticationException(String explanation) {
        super(explanation);
    }

    public TokenAuthenticationException(String username, IResultCode resultCode) {
        super(resultCode.getMsg());
        this.username = username;
        this.resultCode = resultCode;

    }
}
