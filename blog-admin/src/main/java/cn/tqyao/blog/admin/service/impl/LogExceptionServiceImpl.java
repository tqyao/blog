package cn.tqyao.blog.admin.service.impl;

import cn.tqyao.blog.entity.LogException;
import cn.tqyao.blog.dao.LogExceptionDao;
import cn.tqyao.blog.admin.service.LogExceptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理-异常日志 服务实现类
 * </p>
 *
 * @author tqyao
 * @since 2021-04-13
 */
@Service
public class LogExceptionServiceImpl extends ServiceImpl<LogExceptionDao, LogException> implements LogExceptionService {

}
