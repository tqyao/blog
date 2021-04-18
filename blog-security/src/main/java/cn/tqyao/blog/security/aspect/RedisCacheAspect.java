package cn.tqyao.blog.security.aspect;


import cn.tqyao.blog.security.annotation.CacheException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis 缓存异常捕获切面
 * 用于处理读取缓存redis宕机出现的异常的统一捕获，
 * 防止 redis 宕机一切依赖于缓存的业务（如认证过滤器中缓存加载用户信息、资源信息）也无法正常运行
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2021/4/13 20:52  <br>
 * @version 1.0.0 <br>
 */
@Slf4j
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {

    @Pointcut("execution(public * cn.tqyao.blog.*.servoce.*CacheService.*(..))")
    public void cacheAspect() {
    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            // 有CacheException需要抛出异常，如发送验证码业务
            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            }else {
                log.error(throwable.getMessage());
            }
        }
        return result;
    }
}
