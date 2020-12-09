/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.controller;

import cn.tqyao.blog.common.result.Result;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/7 10:54 <br>
 */

@Api(tags = "测试-控制层")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @ApiOperation(value = "测试集合")
    @PostMapping("/collection")
    public Result testCollection(@RequestBody List<String> list){
        return Result.success(list);
    }

    @ApiOperation(value = "测试对象包含集合")
    @PostMapping("/obj")
    public Result testCollection2(@RequestBody TestObjectParam param){
        return Result.success(param);
    }

    @ApiOperation(value = "测试获取请求头部的参数")
    @GetMapping("/header")
    public Result testGetHeaderParam(@ApiIgnore @RequestHeader(value = "Authorization",required = false) String header){
        System.out.println(header);
        return Result.success(header);
    }

    @ApiOperation(value = "测试获取请求头部的参数2")
    @GetMapping("/header2")
    public Result testGetHeaderParam2(@RequestHeader HttpHeaders headers){
        headers.get("Authorization").forEach(System.out::println);
        return Result.success();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestObjectParam{

        private List<String> list;
        private String str;
        @ApiModelProperty(value = "TestObjectAttribute属性值")
        private List<TestObjectAttribute> attributes;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestObjectAttribute {
        private String attributeStr;
        private Integer attributeIn;
    }

}
