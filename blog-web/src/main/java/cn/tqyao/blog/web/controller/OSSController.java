/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.web.controller;

import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.web.service.IOSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * .<br>
 * 文件上传下载
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/21 16:17 <br>
 */
@RestController
@RequestMapping("/api/files")
@Api(tags = "文件上传、下载")
public class OSSController {

    @Autowired
    private IOSSService iossService;

    @ApiOperation(value = "文件上传")
    @PostMapping(value = "/file/upload")
    @ApiImplicitParam(name = "file", value = "文件", paramType = "form",
            dataType = "MultipartFile", dataTypeClass = MultipartFile.class)
    public Result<String> upLoad(@RequestParam("file") MultipartFile file){
        return Result.success(iossService.upLoad(file));
    }

}
