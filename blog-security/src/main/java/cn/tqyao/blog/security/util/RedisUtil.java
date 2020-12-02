/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.util;

import cn.tqyao.blog.common.redis.RedisService;
import cn.tqyao.blog.security.JwtTokenTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;


/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/25 18:22 <br>
 */
public class RedisUtil {


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
     * b:blacklist
     */
    @Value("${redis.blacklist}")
    private String blacklist;


    @Autowired
    private RedisService redisService;


    /**
     * 添加黑名单 Set
     * @param accessToken
     * @param refreshToken
     */
    public void addBlacklistSet(String accessToken, String refreshToken){
        redisService.sAddSetTime(blacklist, ACCESS_TOKEN_EXPIRATION, accessToken);
        redisService.sAddSetTime(blacklist, REFRESH_TOKEN_EXPIRATION, refreshToken);
    }

    /**
     * 检查token在黑名单中是否存在 Set
     * @param token
     * @return
     */
    public boolean checkInBlacklistSet(String token){
        return redisService.sIsMember(blacklist, token);
    }

}
