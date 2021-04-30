/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.util;

import cn.hutool.core.codec.Base64;
import cn.tqyao.blog.security.domain.JwtTokenTypeEnum;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static cn.tqyao.blog.security.domain.JwtTokenTypeEnum.ACCESS_TOKEN;
import static cn.tqyao.blog.security.domain.JwtTokenTypeEnum.REFRESH_TOKEN;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/19 19:41 <br>
 */
@Slf4j
@Getter
public class JWTTokenUtil {

    private static final String CLAIM_KEY_TYPE = "type";

    /**
     * blog-web-secret
     */
    @Value("${jwt.secret}")
    private String SECRET;
    /**
     * 604800
     */
    @Value("${jwt.refresh-token-expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;
    /**
     * 7200
     */
    @Value("${jwt.access-token-expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;
    /**
     * Bearer
     */
    @Value("${jwt.token-head}")
    private String TOKEN_HEAD;
    /**
     * Authorization
     */
    @Value("${jwt.token-header}")
    private String TOKEN_HEADER;
    /**
     * refresh_token
     */
    @Value("${jwt.refresh-token-header}")
    private String REFRESH_TOKEN_HEADER;



    /**
     * 生成token存入userId、username
     * @param jwtTokenTypeEnum
     * @return
     */
    public String generateToken(JwtTokenTypeEnum jwtTokenTypeEnum){
        return generateToken("", "", null, jwtTokenTypeEnum);
    }

    /**
     * 生成token存入userId、username
     * @param username
     * @param username
     * @param jwtTokenTypeEnum
     * @return
     */
    public String generateToken(String username, Date created, JwtTokenTypeEnum jwtTokenTypeEnum){
        return generateToken("", username, created, jwtTokenTypeEnum);
    }
    /**
     * 生成token存入userId、username
     * @param userId
     * @param username
     * @param jwtTokenTypeEnum
     * @return
     */
    public String generateToken(String userId, String username, Date created, JwtTokenTypeEnum jwtTokenTypeEnum){
        Map<String, Object> claims = new HashMap<>();
        if (StringUtils.isNotBlank(userId)) {
            claims.put(Claims.ID, userId);
        }
        if (StringUtils.isNotBlank(username)) {
            claims.put(Claims.SUBJECT, username);
        }
        if (null != created) {
            claims.put(Claims.ISSUED_AT, created.getTime());
        } else {
            claims.put(Claims.ISSUED_AT, new Date().getTime());
        }
        switch (jwtTokenTypeEnum){
            case ACCESS_TOKEN:
                claims.put(CLAIM_KEY_TYPE, ACCESS_TOKEN.getTokenType());
                return generateToken(claims, ACCESS_TOKEN_EXPIRATION);
            case REFRESH_TOKEN:
                claims.put(CLAIM_KEY_TYPE, REFRESH_TOKEN.getTokenType());
                return generateToken(claims, REFRESH_TOKEN_EXPIRATION);
        }
        return null;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims, Long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                //过期时间
                .setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    public Claims getClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
//        Claims claims = null;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(generalKey())
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            log.info("JWT格式验证失败:{}", token);
//        }
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从载体中获取 token 类型
     * @param claims
     * @return
     */
    public String getTokenTypeFromClaims(Claims claims){
        return (String) claims.get(CLAIM_KEY_TYPE);
    }


//    /**
//     * 从token中获取用户名
//     * @param token
//     * @return
//     */
//    public String getUsernameFromToken(String token) {
//        return (String) getClaimDetailFromToken(token, Claims.SUBJECT);
//    }

//    /**
//     * 从token中获取JWT负载信息
//     * @param token
//     * @param key
//     * @return
//     */
//    private Object getClaimDetailFromToken(String token, String key){
//        String claimKey = "";
//        Object claimValue;
//        if (Claims.SUBJECT.equals(key)) {
//            claimKey = key;
//        }
//        if (Claims.ISSUED_AT.equals(key)) {
//            claimKey = key;
//        }
//        if (CLAIM_KEY_TYPE.equals(key)) {
//            claimKey = key;
//        }
//        try {
//            Claims claims = getClaimsFromToken(token);
//            claimValue = claims.get(claimKey);
//        } catch (Exception e) {
//            //该异常包括：
//            //claim值为null异常（claim = null） token不合法（过期）
//            //key-value（claimKey = ""）不存在异常  claimValue = claims.get("");
//            claimValue = null;
//        }
//        return claimValue;
//    }

//    /**
//     * 验证token是否还有效
//     * @param token 客户端传入的token
//     * @param userDetails 数据库中用户信息
//     * @return
//     */
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }

    /**
     * 验证token是否还有效
     * @param claims 客户端传入的token
     * @param userDetails 数据库中用户信息
     * @return
     */
    public boolean validateToken(Claims claims, UserDetails userDetails) {
        String username = claims.getSubject();
        return username.equals(userDetails.getUsername()) && !isTokenExpired(claims);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(Claims claims) {
        Date expiredDate = claims.getExpiration();
        return expiredDate.before(new Date());
    }


//    /**
//     * 判断token是否已经失效
//     */
//    private boolean isTokenExpired(String token) {
//        Date expiredDate = getExpiredDateFromToken(token);
//        return expiredDate.before(new Date());
//    }
//
//    /**
//     * 从token中获取过期时间
//     */
//    private Date getExpiredDateFromToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return claims.getExpiration();
//    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }



//    /**
//     * 获取请求token
//     *
//     * @param request
//     * @return
//     */
//    public String getToken2(HttpServletRequest request) {
//        String token = request.getHeader(TOKEN_HEADER);
//        String tokenHead = this.TOKEN_HEAD;
//        if (token == null) {
//            token = request.getHeader("token");
//            if (token == null) {
//                return null;
//            }
//            token = token.substring(tokenHead.length());
//        } else if (token.contains(tokenHead)) {
//            token = token.trim();
//            if (!org.springframework.util.StringUtils.startsWithIgnoreCase(token, TOKEN_HEAD)) {
//                return null;
//            }
//            if (token.equalsIgnoreCase(TOKEN_HEAD)) {
//                throw new BadCredentialsException("Empty bearer authentication token");
//            }
//            token = token.substring(tokenHead.length());
//
//        }
//        if ("".equals(token)) {
//            token = null;
//        }
//        return token;
//    }

    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token == null) {
            return null;
        }
        token = token.trim();

        if ("".equals(token)) {
            token = null;
        }

        if (!org.springframework.util.StringUtils.startsWithIgnoreCase(token, TOKEN_HEAD)) {
            return null;
        }
        if (token.equalsIgnoreCase(TOKEN_HEAD)) {
            throw new BadCredentialsException("Empty bearer authentication token");
        }

        return token.substring(TOKEN_HEAD.length());
    }

    /**
     * 获取加密的key
     * @return
     */
    private SecretKey generalKey() {
        byte[] keyBytes = SECRET.getBytes();
        byte[] encodedKey;
        encodedKey = Base64.decode(keyBytes);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
