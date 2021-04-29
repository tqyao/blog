/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.log.aspect;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import cn.tqyao.blog.common.log.domain.WebLog;
import cn.tqyao.blog.common.util.AopUtils;
import com.aliyuncs.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 统一日志处理切面
 * @Order:一个方法被多个 Aspect 拦截时，调用顺序，越小越先执行。
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * .<br>
 * @date Create in 2021/4/11 15:57 <br>
 */

@Slf4j
@Aspect
@Component
@Order(1)
public class WebLogAspect {


    /**
     * execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数)
     * 1）execution(* *(..))
     * //表示匹配所有方法
     * 2）execution(public * com. example.controller.*(..))
     * //表示匹配com. example.controller中所有的public方法
     * 3）execution(* com. example.controller..*.*(..))
     * //表示匹配com. example.controller包及其子包下的所有方法
     */
    @Pointcut("execution(public *  cn.tqyao.blog.*.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

    }

    /**
     * 后置返回
     * 如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     * 参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {

    }

    /**
     * 环绕通知：
     * 注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     * <p>
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis ();
        // 获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
        HttpServletRequest request = attributes.getRequest ();
        //记录请求信息
        WebLog webLog = new WebLog ();
        // 执行方法
        Object result = joinPoint.proceed ();
        // 获取 ApiOperation 上的value描述,赋值给webLog
        AopUtils.getDescriptionFromAnnotations (joinPoint);
        long endTime = System.currentTimeMillis ();
        String urlStr = request.getRequestURL ().toString ();
        webLog.setBasePath (StrUtil.removeSuffix (urlStr, URLUtil.url (urlStr).getPath ()));
        webLog.setIp (request.getRemoteUser ());
        webLog.setMethod (request.getMethod ());
        webLog.setParameter (AopUtils.getRequestParams (joinPoint));
        webLog.setResult (result);
        webLog.setSpendTime ((int) (endTime - startTime));
        webLog.setStarTime (startTime);
        webLog.setUri (request.getRequestURI ());
        webLog.setUrl (request.getRequestURL ().toString ());
        log.info ("{}", JSONUtil.parse (webLog));
        return result;
    }

//    /**
//     * 根据方法和传入参数获取请求参数
//     *
//     * @param method
//     * @param args
//     * @return
//     */
//    private Object getParameter(Method method, Object[] args) {
//        List<Object> argList = new ArrayList<> ();
//        Parameter[] parameters = method.getParameters ();
//        for (int i = 0; i < parameters.length; i++) {
//            //将RequestBody注解修饰的参数作为请求参数
//            RequestBody requestBody = parameters[i].getAnnotation (RequestBody.class);
//            if (requestBody != null) {
//                argList.add (args[i]);
//            }
//            //将RequestParam注解修饰的参数作为请求参数
//            RequestParam requestParam = parameters[i].getAnnotation (RequestParam.class);
//            if (requestParam != null) {
//                Map<String, Object> map = new HashMap<> ();
//                String key = parameters[i].getName ();
//                if (StringUtils.isEmpty (requestParam.value ())) {
//                    key = requestParam.value ();
//                }
//                map.put (key, args[i]);
//                argList.add (map);
//            }
//        }
//        if (argList.size () == 0) {
//            return null;
//        } else if (argList.size () == 1) {
//            return argList.get (0);
//        } else {
//            return argList;
//        }
//    }

}