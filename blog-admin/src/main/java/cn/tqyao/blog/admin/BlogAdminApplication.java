package cn.tqyao.blog.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = "cn.tqyao.blog")
@MapperScan(basePackages = "cn.tqyao.blog.admin.mapper")
@EnableMongoRepositories(basePackages = "cn.tqyao.blog.common.log.repository") //开启 mongodb 支持，指定扫描 Repository路径
@EnableAsync //开启异步支持
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

}
