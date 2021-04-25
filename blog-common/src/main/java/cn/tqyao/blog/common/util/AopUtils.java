package cn.tqyao.blog.common.util;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AOP工具类
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/4/23 15:49 <br>
 */
public class AopUtils {


    /**
     * 获取方法参数键值对
     * @param joinPoint
     * @return
     */
    public static Map<String, Object> getRequestParams(JoinPoint joinPoint) {
        Map<String, Object> map = new LinkedHashMap<> ();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature ()).getParameterNames ();
        Object[] args = joinPoint.getArgs ();
        for (int i = 0; i < args.length; i++) {
            if (!isFilterObject (args[i])) {
                map.put (parameterNames[i], args[i]);
            }
        }
        return map;
    }

    /**
     * 获取注解描述信息
     * @param joinPoint
     * @return
     */
    public static String getDescriptionFromAnnotations(JoinPoint joinPoint) {
        String description = "";
        Method method = ((MethodSignature) joinPoint.getSignature ()).getMethod ();
        ApiOperation annotation = method.getAnnotation (ApiOperation.class);
        if (annotation != null) {
            description = annotation.value ();
        }
        return description;
    }


    /**
     * consider if the data is file, httpRequest or response
     *
     * @param o the data
     * @return if match return true, else return false
     */
    private static boolean isFilterObject(final Object o) {
        return o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof MultipartFile;
    }
}
