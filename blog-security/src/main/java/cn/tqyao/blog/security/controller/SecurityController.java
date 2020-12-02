/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.security.controller;

import cn.tqyao.blog.security.JwtAuthenticationToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * .<br>
 * TODO 刷新token接口
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/11/21 19:53 <br>
 */
@RestController("/security")
public class SecurityController {

    @ApiOperation(value = "刷新token")
    @GetMapping("/{refresh-token}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "refreshToken", value = "refreshToken", required = true, paramType = "path", dataType = "String")
    })
    public JwtAuthenticationToken refreshToken(@PathVariable("refresh-token") String refreshToken){

        return null;
    }
}
