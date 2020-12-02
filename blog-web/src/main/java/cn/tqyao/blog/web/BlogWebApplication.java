/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/9 19:16 <br>
 */
@SpringBootApplication(scanBasePackages = "cn.tqyao.blog")
@MapperScan(basePackages = "cn.tqyao.blog.web.mapper")
//@ComponentScans(value = {})
public class BlogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogWebApplication.class, args);
    }
}
