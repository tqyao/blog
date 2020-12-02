/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.handle;

import cn.tqyao.blog.common.result.IResultCode;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.common.result.ResultCode;
import cn.tqyao.blog.security.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/19 21:53 <br>
 */
@Slf4j
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("SpringSecurity异常, uri: {} , caused by: ", request.getRequestURI(), exception);
        IResultCode resultCode = ResultCode.FAILED;
        if (exception instanceof LockedException){
            resultCode = ResultCode.USER_LOCKED_ERROR;
        }else if (exception instanceof CredentialsExpiredException){
            resultCode = ResultCode.USER_CREDENTIALS_EXPIRE_ERROR;
        }else if (exception instanceof AccountExpiredException){
            resultCode = ResultCode.USER_ACCOUNT_EXPIRED_ERROR;
        }else if (exception instanceof DisabledException){
            resultCode = ResultCode.USER_DISABLED_ERROR;
        }else if (exception instanceof BadCredentialsException){
            resultCode = ResultCode.USER_BAD_CREDENTIALS_ERROR;
        }
        ResponseUtil.send(response, Result.custom(resultCode));
    }
}
