package cn.tqyao.blog.common.log.service;

import cn.tqyao.blog.common.log.domain.ExceptionLog;
import org.springframework.scheduling.annotation.Async;


/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/4/23 15:49 <br>
 */
public interface ExceptionLogService {

    @Async
    void save(ExceptionLog exceptionLog);

}
