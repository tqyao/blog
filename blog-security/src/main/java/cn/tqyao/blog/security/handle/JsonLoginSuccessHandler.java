/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.handle;

import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.security.util.ResponseUtil;
import cn.tqyao.blog.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/15 15:24 <br>
 */
@Slf4j
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功, uri: {}", request.getRequestURI());
        ResponseUtil.send(response, Result.success("登录成功！",securityUtil.getLoginSuccessToken(authentication)));
    }
}
