/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/13 9:41 <br>
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    private String webDatabase;

    private String blacklist;

    private final Key key = new Key();

    private final Expire expire = new Expire();

    @Getter
    @Setter
    public static class Key {
        private String authCode;
        private String member;
    }

    @Getter
    @Setter
    public static class Expire {
        private Long authCode;
        private Long common;
    }


}
