/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.util;

import cn.tqyao.blog.common.base.BasePageDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/6 14:06 <br>
 */
public class PageUtil {

    /**
     * 获取分页信息
     * @param pageDTO
     * @param <T>
     * @return
     */
    public static <T> Page<T> getPage(BasePageDTO pageDTO){
        Page<T> p = new Page<>();
        return Optional.ofNullable(pageDTO).map(page -> {
            Long size = page.getSize();
            Long current = page.getCurrent();
            if (ObjectUtils.isEmpty(size) && ObjectUtils.isEmpty(current)) {
                return p.setCurrent(1L).setSize(100L);
            }
            if (ObjectUtils.isEmpty(size)) {
                return p.setCurrent(current).setSize(100L);
            }
            if (ObjectUtils.isEmpty(current)) {
                return p.setCurrent(1L).setSize(size);
            }
            return p.setCurrent(current).setSize(size);
        }).orElse(new Page<>(1L, 100L));
    }
}
