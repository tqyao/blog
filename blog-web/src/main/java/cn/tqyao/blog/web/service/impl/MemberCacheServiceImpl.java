/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.redis.RedisService;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.web.config.RedisProperties;
import cn.tqyao.blog.web.service.IMemberCacheService;
import cn.tqyao.blog.web.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/13 9:01 <br>
 */
@Service
public class MemberCacheServiceImpl implements IMemberCacheService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private IMemberService memberService;

    private String REDIS_DATABASE;
    private Long REDIS_EXPIRE;
    private Long REDIS_EXPIRE_AUTH_CODE;
    private String REDIS_KEY_MEMBER;
    private String REDIS_KEY_AUTH_CODE;

    @PostConstruct
    private void init(){
        REDIS_DATABASE = redisProperties.getWebDatabase();
        REDIS_EXPIRE = redisProperties.getExpire().getCommon();
        REDIS_EXPIRE_AUTH_CODE = redisProperties.getExpire().getAuthCode();
        REDIS_KEY_MEMBER = redisProperties.getKey().getMember();
        REDIS_KEY_AUTH_CODE = redisProperties.getKey().getAuthCode();
    }

    @Override
    public void delMember(String memberId) {
        Optional.ofNullable(memberService.getById(memberId)).ifPresent(member -> {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
            redisService.del(key);
        });
    }

    @Override
    public Member getMember(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + username;
        return (Member) redisService.get(key);
    }

    @Override
    public void setMember(Member member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getUsername();
        redisService.set(key, member, REDIS_EXPIRE);
    }

    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key,authCode,REDIS_EXPIRE_AUTH_CODE);
    }

    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }
}
