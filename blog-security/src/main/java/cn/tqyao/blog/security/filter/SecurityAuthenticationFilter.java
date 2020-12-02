/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.filter;

import cn.tqyao.blog.security.JwtAuthenticationToken;
import cn.tqyao.blog.security.exception.TokenAuthenticationException;
import cn.tqyao.blog.security.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/20 8:56 <br>
 */
@Slf4j
public class SecurityAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private SecurityUtil securityUtil;

    private AuthenticationEntryPoint authenticationEntryPoint;


    public SecurityAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication;
        try {
            authentication = securityUtil.checkTokenValid(httpServletRequest);
            if (null != authentication) {
                if (authentication instanceof JwtAuthenticationToken) {
                    JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (TokenAuthenticationException e) {
            authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
            return;
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
