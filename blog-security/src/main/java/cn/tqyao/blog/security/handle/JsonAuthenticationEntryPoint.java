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
 * token 认证失败端点
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
//        if (authException instanceof TokenAuthenticationException) {
//            log.warn("认证失败 -> token 错误：, uri:{}, caused by:{}",
//                    request.getRequestURI() ,authException);
//            TokenAuthenticationException tokenException = (TokenAuthenticationException) authException;
//            ResponseUtil.send(response, Result.custom(tokenException.getResultCode()));
//        } else {
//            log.warn("认证失败 -> 未登录认证, uri: {} , caused by: {}",
//                    request.getRequestURI(), authException);
//            ResponseUtil.send(response, Result.custom(ResultCode.UNAUTHORIZED));
//        }

        if (authException instanceof TokenAuthenticationException) {
            log.warn ("认证失败 -> token 错误：, uri:{}, caused by:{}",
                    request.getRequestURI (), authException);
            ResponseUtil.send(response,
                    Result.custom(((TokenAuthenticationException) authException).getResultCode()));
        } else {
            log.warn("认证失败 ->  uri: {} , caused by: {}",
                    request.getRequestURI(), authException);
            ResponseUtil.send(response, Result.custom(ResultCode.UNAUTHORIZED));
        }

    }
}
