/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.util.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/22 19:14 <br>
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.dayu-sms")
public class AliyunSmsSendProperties {

    private String product;

    private String domain;

    private String accessKeyId;

    private String accessKeySecret;

    private String templateCode;

    private String signName;

}
