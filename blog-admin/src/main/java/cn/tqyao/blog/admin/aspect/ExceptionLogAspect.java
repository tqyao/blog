//package cn.tqyao.blog.admin.aspect;
//
//
//import cn.tqyao.blog.entity.LogException;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * todo：异常日志记录切面
// * 用于把切点方法抛出的异常记录到数据库中
// *
// * @author tanqinyao<br>
// * @version 1.0.0 <br>
// * .<br>
// * @date Create in 2021/4/11 15:57 <br>
// */
////@Slf4j
////@Component
////@Aspect
////@Order(2)
//public class ExceptionLogAspect {
//
////    private LogExceptionService logExceptionService;
//
//    @Pointcut("execution(* cn.tqyao.blog.*.controller.*.*(..))")
//    public void logPointCut() {
//
//    }
//
//    @Before ("logPointCut()")
//    public void before(){
//        System.out.println ("before...");
//    }
//    @AfterThrowing(value = "logPointCut()", throwing = "exception")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
//        LogException log = handleLog(joinPoint, exception);
//        System.out.println ("AfterThrowing...");
//    }
//
//    private LogException handleLog(JoinPoint joinPoint, Throwable exception) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes ();
//        HttpServletRequest request = requestAttributes.getRequest ();
//        String uri = request.getRequestURI ();
//        String method = request.getMethod ();
//        return null;
//    }
//}
