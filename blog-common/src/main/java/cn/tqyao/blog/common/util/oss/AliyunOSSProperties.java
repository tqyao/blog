/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.util.oss;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/21 16:58 <br>
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {

    /**
     * 阿里云 oss 站点
     */
//    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 阿里云 oss 资源访问 url
     */
//    @Value("${oss.url}")
    private String url;

    /**
     * 阿里云 oss 公钥
     */
//    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云 oss 私钥
     */
//    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 阿里云 oss 文件根目录
     */
//    @Value("${oss.bucketName}")
    private String bucketName;
}
