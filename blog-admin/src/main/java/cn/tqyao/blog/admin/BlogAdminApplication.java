package cn.tqyao.blog.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.tqyao.blog")
@MapperScan(basePackages = "cn.tqyao.blog.admin.mapper")
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

}
