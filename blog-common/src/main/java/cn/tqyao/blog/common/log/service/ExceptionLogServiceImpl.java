package cn.tqyao.blog.common.log.service;

import cn.tqyao.blog.common.log.domain.ExceptionLog;
import cn.tqyao.blog.common.log.repository.ExceptionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/4/23 15:49 <br>
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {

    @Autowired
    private ExceptionLogRepository exceptionLogRepository;

    @Override
    public void save(ExceptionLog exceptionLog) {
        exceptionLogRepository.save (exceptionLog);
    }


}
