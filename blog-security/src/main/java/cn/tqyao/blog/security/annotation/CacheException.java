package cn.tqyao.blog.security.annotation;

import java.lang.annotation.*;


/**
 * 用于标记，让 RedisCacheAspect 切面抛出异常，而不是捕获处理
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2021/4/13 20:52  <br>
 * @version 1.0.0 <br>
 */
@Documented
@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
public @interface CacheException {
}
