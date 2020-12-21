/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/21 16:32 <br>
 */
public interface IOSSService {

    String upLoad(MultipartFile file);

    void removeFile(String path);
}
