/**
 * Copyright 2020-2030 The author personally reserves all rights.
 */
package cn.tqyao.blog.common.exception;

import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.common.result.ResultCode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统异常处理
 * .<br>
 *
 * @author tanqinyao<br>
 * @version 1.0.0 <br>
 * @date Create in 2020/12/16 11:14 <br>
 */
@RestController
public class JsonErrorController implements ErrorController {

    private final static String ERROR_PREFIX = "error_";
    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestBody
    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.OK)
    public Result errorJson(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == HttpStatus.NO_CONTENT.value()) {
            return Result.success();
        }

        String key = ERROR_PREFIX + statusCode;
        try {
            return HttpStatusEnum.valueOf(key).Json();
        } catch (Exception e) {
            return HttpStatusEnum.error_500.Json();
        }
    }


    enum HttpStatusEnum {
        error_400(){
            public Result Json(){
                return Result.custom(ResultCode.BAD_REQUEST);
            }
        },
        error_401(){
            public Result Json(){
                return Result.custom(ResultCode.UNAUTHORIZED);
            }
        },
        error_403(){
            public Result Json(){
                return Result.custom(ResultCode.FORBIDDEN);
            }
        },
        error_404(){
            public Result Json(){
                return Result.custom(ResultCode.NOT_FOUND);
            }
        },
        error_500(){
            public Result Json(){
                return Result.custom(ResultCode.INTERNAL_SERVER_ERROR);
            }
        };

        public abstract Result Json();



    }

}
