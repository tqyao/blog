/**
 * Copyright 2020-2030 Jinhui Technology Co. LTD  All Rights Reserved.
 */
package cn.tqyao.blog.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author James Guo<br>
 * @version 1.0.0 <br>
 * @date Create in 2020-10-19 12:18 <br>
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 自动填充数据库创建人、创建时间、更新人、更新时间
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandlerConfig());
        return globalConfig;
    }
}
