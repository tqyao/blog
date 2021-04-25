package cn.tqyao.blog.common.log.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.tqyao.blog.common.log.domain.ExceptionLog;
import cn.tqyao.blog.common.log.service.ExceptionLogService;
import cn.tqyao.blog.common.util.AopUtils;
import cn.tqyao.blog.common.util.IpAddressUtils;
import cn.tqyao.blog.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * 异常日志切面处理
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * .<br>
 * @Order:一个方法被多个 Aspect 拦截时，调用顺序，越小越先执行。
 * @date Create in 2021/4/23 15:57 <br>
 */
@Slf4j
@Aspect
@Component
@Order(2)
public class ExceptionLogAspect {

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Pointcut("execution(public * cn.tqyao.blog.*.controller.*.*(..))")
    public void logPointCut() {

    }

    /**
     * //4、异常通知：在目标方法出现异常 时会执行的代码，可以访问到异常对象：且可以!!指定在出现特定异常时在执行通知!!,如果是修改为nullPointerException里，只有空指针异常才会执行
     * //    @AfterThrowing(pointcut = "execution(* Spring4_AOP.aopAnnotation.*.*(..))", throwing = "except")
     *
     * @param joinPoint
     * @param exception 可指定切面异常拦截异常类型
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        ExceptionLog log = handleLog (joinPoint, exception);
        exceptionLogService.save (log);
    }

    private ExceptionLog handleLog(JoinPoint joinPoint, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
        HttpServletRequest request = attributes.getRequest ();
        // 获取请求参数
        Map<String, Object> requestParams = AopUtils.getRequestParams (joinPoint);
        String param = StrUtil.sub (JSONUtil.parseFromMap (requestParams).toString (), 0, 2000);
        // 构造 ExceptionLog
        ExceptionLog excLog = new ExceptionLog ();
        excLog
                .setUri (request.getRequestURI ())
                .setMethod (request.getMethod ())
                .setUserAgent (request.getHeader ("User-Agent"))
                .setError (getStackTrace (e))
                .setParam (param)
                .setDescription (AopUtils.getDescriptionFromAnnotations (joinPoint))
                .setIp (IpAddressUtils.getIpAddress (request));
        return excLog;
    }

    /**
     * 获取栈信息
     *
     * @param throwable
     * @return
     */
    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter ();
        try (PrintWriter pw = new PrintWriter (sw)) {
            throwable.printStackTrace (pw);
            return sw.toString ();
        }
    }

}
