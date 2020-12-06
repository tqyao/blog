/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */


import cn.tqyao.blog.common.redis.RedisService;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.web.BlogWebApplication;
import cn.tqyao.blog.web.config.RedisProperties;
import cn.tqyao.blog.web.service.IMemberCacheService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Wrapper;
import java.util.List;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/13 10:13 <br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogWebApplication.class)
public class SpringWebYmlTest {


//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisProperties redisProperties;

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Autowired
    private RedisService redisService;

    @Value("${redis.blacklist}")
    private String blacklist;

    @Autowired
    private IMemberCacheService memberCacheService;

    @Test
    public void printConfigs() {
        redisService.lPush(blacklist,"token",30);
        System.out.println("list size:" + redisService.lSize(blacklist));
        List<Object> objects = redisService.lRange(blacklist, 0, redisService.lSize(blacklist));
        System.out.println("list" + objects);
    }

    @Autowired
    private IMemberService memberService;
    @Test
    public void getTokenBody(){
//        MemberServiceImpl memberService = (MemberServiceImpl) SpringUtil.getBean("memberServiceImpl");
//
//        String tokenBody = memberService.getTokenBody("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cXkiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiZXhwIjoxNjA2MzU5MzIwLCJpYXQiOjE2MDYzNTIxMjA3MTh9.4QyYS91UXk0YNSrafpwkH0vulJ9Yu8OSvVBdZnBiTA02f6wM9F8IM6NeewPOW-QRsjxOZSZM3GNCfmeHblCrrQ");
//        System.out.println("tokenBody==" + tokenBody);
//        System.out.println("-------------------------------");
////        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cXkiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiZXhwIjoxNjA2MzU5MzIwLCJpYXQiOjE2MDYzNTIxMjA3MTh9.4QyYS91UXk0YNSrafpwkH0vulJ9Yu8OSvVBdZnBiTA02f6wM9F8IM6NeewPOW-QRsjxOZSZM3GNCfmeHblCrrQ";
////        System.out.println("memberService.getTokenBody(token)===" + memberService.getTokenBody(token));
//        String tokenBody2 = memberService.getTokenBody("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0cXkiLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiZXhwIjoxNjA2MzU5MzIwLCJpYXQiOjE2MDYzNTIxMjA3MTh9.4QyYS91UXk0YNSrafpwkH0vulJ9Yu8OSvVBdZnBiTA02f6wM9F8IM6NeewPOW-QRsjxOZSZM3GNCfmeHblCrrQ");
//        System.out.println("tokenBody2==" + tokenBody2);
//        System.out.println();
//        System.out.println("tokenBody === tokenBody2 ===>" + tokenBody.equals(tokenBody2));
    }

    @Test
    public void testSecondLevelCache(){
        Member one = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getId,"ae50fd5d13c8544805e7d2f28423d099"));
        System.out.println(one);
        System.out.println("-------------------------");
        Member two = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getId,"ae50fd5d13c8544805e7d2f28423d099"));
        System.out.println(two);
        System.out.println();
        System.out.println(two == one);
    }



}
