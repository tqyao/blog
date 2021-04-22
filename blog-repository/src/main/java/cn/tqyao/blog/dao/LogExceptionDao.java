package cn.tqyao.blog.dao;

import cn.tqyao.blog.entity.LogException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台管理-异常日志 Mapper 接口
 * </p>
 *
 * @author tqyao
 * @since 2021-04-13
 */
@Repository
public interface LogExceptionDao extends BaseMapper<LogException> {

}
