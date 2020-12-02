/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.handle;

import cn.tqyao.blog.common.result.IResultCode;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.common.result.ResultCode;
import cn.tqyao.blog.security.exception.TokenAuthenticationException;

import cn.tqyao.blog.security.util.ResponseUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/21 16:45 <br>
 */
@Slf4j
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        if (authException instanceof TokenAuthenticationException) {
            TokenAuthenticationException tokenException = (TokenAuthenticationException) authException;
            String msg = tokenException.getResultCode().getMsg();
            if (ResultCode.TOKEN_PARSE_ERROR.getMsg().equals(msg)) {
                log.warn("认证失败-> token解析异常:token过期/格式等错误, uri:{}, caused by:{}",
                        request.getRequestURI() ,tokenException);
            }
            if (ResultCode.TOKEN_TYPE_ERROR.getMsg().equals(msg)) {
                log.warn("认证失败-> token类型异常:access_token/refresh_token, uri: {} , caused by: ",
                        request.getRequestURI(), tokenException);
            }
            if (ResultCode.TOKEN_AUTHORIZED_FAIL_ERROR.getMsg().equals(msg)) {
                log.warn("认证失败-> 存在伪造token可能:username: {} 不存在于数据库中, uri: {} , caused by: {}",
                        tokenException.getUsername() , request.getRequestURI(), tokenException);
            }
            if (ResultCode.TOKEN_INVALIDATION_ERROR.getMsg().equals(msg)) {
                log.warn("认证失败-> 无效token:存在于黑名单中, uri: {} , caused by: {}",
                        request.getRequestURI(), tokenException);
            }
            ResponseUtil.send(response, Result.custom(tokenException.getResultCode()));
        } else {
            ResponseUtil.send(response, Result.custom(ResultCode.UNAUTHORIZED));
        }

    }
}
