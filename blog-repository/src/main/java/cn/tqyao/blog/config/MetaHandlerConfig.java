/**
 * Copyright 2020-2030 Jinhui Technology Co. LTD  All Rights Reserved.
 */
package cn.tqyao.blog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/5 11:36  <br>
 * @version 1.0.0 <br>
*/
@Component
public class MetaHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
