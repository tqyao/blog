/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.service.impl;

import cn.tqyao.blog.common.util.oss.AliyunOSSBootUtil;
import cn.tqyao.blog.common.util.oss.AliyunOSSProperties;
import cn.tqyao.blog.web.service.IOSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/21 16:32 <br>
 */
@Service
public class OSSServiceImpl implements IOSSService {

    @Autowired
    private AliyunOSSProperties properties;

    @Override
    public String upLoad(MultipartFile file) {
        return AliyunOSSBootUtil.upload(properties,file);
    }

    @Override
    public void removeFile(String path) {
        AliyunOSSBootUtil.remove(properties, path);
    }
}
