/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */


import cn.tqyao.blog.common.redis.RedisService;
import cn.tqyao.blog.entity.Article;
import cn.tqyao.blog.entity.Member;
import cn.tqyao.blog.web.BlogWebApplication;
import cn.tqyao.blog.web.config.RedisProperties;
import cn.tqyao.blog.web.mapper.ArticleMapper;
import cn.tqyao.blog.web.service.IMemberCacheService;
import cn.tqyao.blog.web.service.IMemberService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
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


    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.blacklist}")
    private String blacklist;
    @Autowired
    private RedisService redisService;

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
    public void testSecondLevelCache(){
        Member one = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getId,"ae50fd5d13c8544805e7d2f28423d099"));
        System.out.println(one);
        System.out.println("-------------------------");
        Member two = memberService.getOne(Wrappers.<Member>lambdaQuery().eq(Member::getId,"ae50fd5d13c8544805e7d2f28423d099"));
        System.out.println(two);
        System.out.println();
        System.out.println(two == one);
    }



    /**
     * 测试mybatis缓存
     */
    @Autowired
    private SqlSessionFactory factory;

    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
    }

    /**
     * 本地二级缓存
     */
    @Test
    public void testMysqlTwoLevelCache(){

        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        ArticleMapper mapper1 = sqlSession1.getMapper(ArticleMapper.class);
        ArticleMapper mapper2 = sqlSession2.getMapper(ArticleMapper.class);

        Article article1 = mapper1.selectById("1972adeb66db69cca706a2ce180dd4b4");
        Article article2 = mapper2.selectById("1972adeb66db69cca706a2ce180dd4b4");

        System.out.println(article1);
        System.out.println(article2);

        System.out.println("article1 == article2 => " + (article1 == article2));
        System.out.println("article1.equals(article2) =>" + article1.equals(article2));

        sqlSession1.close();
        sqlSession2.close();
    }

    @Test
    public void testMysqlOneLevelCache(){
        SqlSession sqlSession = factory.openSession(true);
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);

        System.out.println(mapper.selectById("1972adeb66db69cca706a2ce180dd4b4"));
        System.out.println(mapper.selectById("1972adeb66db69cca706a2ce180dd4b4"));
        System.out.println(mapper.selectById("1972adeb66db69cca706a2ce180dd4b4"));
        sqlSession.close();
    }


}
