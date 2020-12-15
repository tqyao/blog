/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.util;

import cn.tqyao.blog.common.redis.RedisService;
import cn.tqyao.blog.common.result.ResultCode;
import cn.tqyao.blog.security.JwtAuthenticationToken;
import cn.tqyao.blog.security.JwtTokenTypeEnum;
import cn.tqyao.blog.security.exception.TokenAuthenticationException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cn.tqyao.blog.security.JwtTokenTypeEnum.ACCESS_TOKEN;
import static cn.tqyao.blog.security.JwtTokenTypeEnum.REFRESH_TOKEN;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/19 20:20 <br>
 */
@Slf4j
public class SecurityUtil {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 登录成功返回token
     * @param authentication
     * @return
     */
    public JwtAuthenticationToken getLoginSuccessToken(Authentication authentication){
        String username = getUsername(authentication);
        Date date = new Date();
        return new JwtAuthenticationToken(null,null,jwtTokenUtil.generateToken(username, date, ACCESS_TOKEN),
                jwtTokenUtil.generateToken(username, date, REFRESH_TOKEN));
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    public JwtAuthenticationToken getRefreshToken(String refreshToken){
        return getLoginSuccessToken(checkRefreshTokenValid(refreshToken));
    }

    /**
     * 验证 access_token 并返回 Authentication
     * @param request
     * @return
     */
    public Authentication checkAccessTokenValid(HttpServletRequest request){
        //获取请求头token
        String token;
        try {
            token = jwtTokenUtil.getToken(request);
        } catch (BadCredentialsException e) {
            //异常转换
            throw new TokenAuthenticationException(ResultCode.TOKEN_HEAD_ERROR);
        }
        if (StringUtils.isBlank(token)) {
            return null;
        }

        Authentication authentication;
        if ((authentication = SecurityContextHolder.getContext().getAuthentication()) != null) {
            return authentication;
        }

        return checkTokenValid(token, ACCESS_TOKEN);
    }

    /**
     * 验证 refresh_token 并返回 Authentication
     * @param refreshToken
     * @return
     */
    public Authentication checkRefreshTokenValid(String refreshToken){

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            SecurityContextHolder.clearContext();
        }

        return checkTokenValid(refreshToken, REFRESH_TOKEN);
    }

    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    private Authentication checkTokenValid(String token, JwtTokenTypeEnum jwtTokenType) {
        UserDetails userDetails;
        try {
            //解析 token 获取负载信息，全过程只需解析一次
            Claims claims = jwtTokenUtil.getClaimsFromToken(token);

            String username = claims.getSubject();

            if (ACCESS_TOKEN.equals(jwtTokenType)) {
                log.info("checking username:{}", username);
            }

            if (null == username) {
                log.warn("username is null");
                return null;
            }
            String tokenType = jwtTokenUtil.getTokenTypeFromClaims(claims);
            if (null == tokenType) {
                log.warn("tokenType is null");
                return null;
            }

            //检查token类型
            if (!jwtTokenType.getTokenType().equals(tokenType)) {
                throw new TokenAuthenticationException(ResultCode.TOKEN_TYPE_ERROR);
            }

            userDetails = userDetailsService.loadUserByUsername(username);
            if (!jwtTokenUtil.validateToken(claims, userDetails)) {
                throw new TokenAuthenticationException(username,ResultCode.TOKEN_AUTHORIZED_FAIL_ERROR);
            }

            // 查询redis中是否存在该token
            if (redisUtil.checkInBlacklistSet(token)) {
                throw new TokenAuthenticationException(username, ResultCode.TOKEN_INVALIDATION_ERROR);
            }

            if (ACCESS_TOKEN.equals(jwtTokenType)) {
                log.info("authenticated username:{}", username);
            }

        } catch (JwtException e) {
            //异常转换
            log.info("JWT格式验证失败:{}", token);
            return null;
        }
        return new JwtAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
    }

//
//    /**
//     * 检查token是否有效
//     * @param request
//     * @return
//     */
//    public Authentication checkTokenValid2(HttpServletRequest request, JwtTokenTypeEnum jwtTokenType) {
//        String token = "";
//        UserDetails userDetails;
//        try {
//            //获取请求头token
//            token = jwtTokenUtil.getToken(request);
//            if (StringUtils.isBlank(token)) {
//                return null;
//            }
//
//            Authentication authentication;
//            if ((authentication = SecurityContextHolder.getContext().getAuthentication()) != null) {
//                return authentication;
//            }
//
//            //解析 token 获取负载信息，全过程只需解析一次
//            Claims claims = jwtTokenUtil.getClaimsFromToken(token);
//
//            String username = claims.getSubject();
//
//            log.info("checking username:{}", username);
//            if (null == username) {
//                log.warn("username is null");
//                return null;
//            }
//            String tokenType = jwtTokenUtil.getTokenTypeFromClaims(claims);
//            if (null == tokenType) {
//                log.warn("tokenType is null");
//                return null;
//            }
//
//            //检查token类型
//            if (!jwtTokenType.getTokenType().equals(tokenType)) {
//                throw new TokenAuthenticationException(ResultCode.TOKEN_TYPE_ERROR);
//            }
//
//            userDetails = userDetailsService.loadUserByUsername(username);
//            if (!jwtTokenUtil.validateToken(claims, userDetails)) {
//                throw new TokenAuthenticationException(username,ResultCode.TOKEN_AUTHORIZED_FAIL_ERROR);
//            }
//
//            // 查询redis中是否存在该token
//            if (redisUtil.checkInBlacklistSet(token)) {
//                throw new TokenAuthenticationException(username, ResultCode.TOKEN_INVALIDATION_ERROR);
//            }
//
//            log.info("authenticated username:{}", username);
//
//        } catch (BadCredentialsException e) {
//            throw new TokenAuthenticationException(ResultCode.TOKEN_HEAD_ERROR);
//        } catch (JwtException e) {
//            log.info("JWT格式验证失败:{}", token);
//            throw new TokenAuthenticationException(ResultCode.TOKEN_PARSE_ERROR);
//        }
//
//        return new JwtAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
//    }



    /**
     * 获取用户名根据认证信息
     * @param authentication
     * @return
     */
    public String getUsername(Authentication authentication){
        String username = null;
        if (null != authentication) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
            if (principal instanceof String) {
                username = (String) principal;
            }
        }
        return username;
    }



}
