/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.controller;

import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.web.service.HomeService;
import cn.tqyao.blog.web.vo.HomeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/18 11:51 <br>
 */
@Ignore
@Api(tags = "博客-首页")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation(value = "PC首页内容展示")
    @GetMapping()
    public Result<HomeVO> PCHome(){
        return Result.success(homeService.getHomeContent());
    }


}
