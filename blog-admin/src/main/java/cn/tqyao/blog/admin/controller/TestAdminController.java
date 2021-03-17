/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.admin.controller;

import cn.tqyao.blog.admin.mapper.SysAdminMapper;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.entity.SysAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2021/3/15 22:40 <br>
 */

@RestController
@RequestMapping("/test-admins")
public class TestAdminController {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @GetMapping("/test")
    public Result adminControllerTest(@RequestParam String str){
        System.out.println (str);
        SysAdmin admin = new SysAdmin ();
        admin
                .setUsername (str)
                .setPassword ("123");

        sysAdminMapper.insert (admin);
        return Result.success (str);
    }
}
