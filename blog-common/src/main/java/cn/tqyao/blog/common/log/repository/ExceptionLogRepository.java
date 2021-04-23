package cn.tqyao.blog.common.log.repository;

import cn.tqyao.blog.common.log.domain.ExceptionLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/4/23 15:49 <br>
 */
public interface ExceptionLogRepository extends MongoRepository<ExceptionLog,String> {
    
}
