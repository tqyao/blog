package cn.tqyao.blog.security.aspect;


/**
 * Redis 缓存切面
 * 用于处理读取缓存redis宕机出现的异常的统一捕获，
 * 防止 redis 宕机一切依赖于缓存的业务（如认证过滤器中缓存加载用户信息、资源信息）也无法正常运行
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2021/4/13 20:52  <br>
 * @version 1.0.0 <br>
 */
public class RedisCacheAspect {
}
