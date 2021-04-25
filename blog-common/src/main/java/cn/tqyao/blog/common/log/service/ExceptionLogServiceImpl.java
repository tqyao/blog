package cn.tqyao.blog.common.log.service;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentInfo;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.tqyao.blog.common.log.domain.ExceptionLog;
import cn.tqyao.blog.common.log.repository.ExceptionLogRepository;
import cn.tqyao.blog.common.util.IpAddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        UserAgent userAgent = UserAgentUtil.parse (exceptionLog.getUserAgent ());
        exceptionLog
                .setOs (userAgent.getOs ().toString ())
                .setBrowser (userAgent.getBrowser ().toString ())
                .setIpSource (IpAddressUtils.getCityInfo (exceptionLog.getIp ()));
        exceptionLogRepository.save (exceptionLog);
    }


}
