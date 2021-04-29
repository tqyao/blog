package cn.tqyao.blog.security.filter;


import cn.tqyao.blog.security.domain.JwtAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * JWT 登录认证过了器
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/15 14:05  <br>
 * @version 1.0.0 <br>
*/
@Slf4j
public class SecurityLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //判断 ContentType 类型
        if (request.getContentType().contains (MediaType.APPLICATION_JSON_VALUE)) {
            // 获取请求内容
            Map<String, String> loginData = new HashMap<>(2);
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }

            // 创建 Authentication
            UsernamePasswordAuthenticationToken authentication =
                    new JwtAuthenticationToken(username, password);
            setDetails(request, authentication);

            // 执行身份验证
            return this.getAuthenticationManager().authenticate(authentication);
        } else {
            // 兼容表单登陆
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // 存储登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authResult);
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }
}
