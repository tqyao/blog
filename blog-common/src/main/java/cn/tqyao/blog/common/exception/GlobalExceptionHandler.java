/**
 * Copyright 2020-2030 Jinhui Technology Co. LTD  All Rights Reserved.
 */
package cn.tqyao.blog.common.exception;

import cn.tqyao.blog.common.result.ParameterInvalidResult;
import cn.tqyao.blog.common.result.Result;
import cn.tqyao.blog.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 全局系统异常处理
 * .<br>
 * @author tanqinyao<br>
 * @date Create in 2020/11/5 11:22  <br>
 * @version 1.0.0 <br>
*/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义通用异常捕获处理
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public Result handleCommonException(CommonException e, HttpServletRequest request) {
        log.warn("common 公共异常，uri：{}，caused by：", request.getRequestURI(), e);
        if (e.getResultCode() != null) {
            //已定义异常
            return Result.custom(e.getResultCode());
        }
        //未定义异常（业务异常）
        return Result.error(e.getMessage());
    }

    /**
     * controller单一参数校验异常
     * controller类一定要加 @Validated
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){
        log.warn("参数校验不合法 异常，uri：{}，caused by：{}", request.getRequestURI(), e.getMessage());
        List<ParameterInvalidResult> parameterInvalidItemList = new ArrayList<>();
        Optional.ofNullable(e.getConstraintViolations()).ifPresent(cvSet -> {
            cvSet.forEach(cv -> {
                ParameterInvalidResult parameterInvalidResult = new ParameterInvalidResult();
                String[] messageTemplate = cv.getMessageTemplate().split(" ");
                parameterInvalidResult.setFieldName(messageTemplate[0]);
                parameterInvalidResult.setMessages(Collections.singletonList(messageTemplate[1]));
                parameterInvalidItemList.add(parameterInvalidResult);
            });
        });
        return Result.custom(ResultCode.VALIDATE_FAILED,parameterInvalidItemList);
    }


    /**
     * javaBean（json）参数异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("参数校验不合法 异常，uri：{}，caused by：{}", request.getRequestURI(), e.getMessage());
        return validateFail(e);
    }

    /**
     * javaBean（from-data）参数异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e, HttpServletRequest request){
        log.warn("参数校验不合法 异常，uri：{}，caused by：{}", request.getRequestURI(), e.getMessage());
        return validateFail(e);
    }

    private Result validateFail(Exception e){
        List<ParameterInvalidResult> parameterInvalidResult = null;
        if (e instanceof BindException) {
            BindException be = (BindException) e;
            parameterInvalidResult = getParameterInvalidResult(be);
        }
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            parameterInvalidResult = getParameterInvalidResult(me);
        }

        if (CollectionUtils.isEmpty(parameterInvalidResult)){
            return Result.custom(ResultCode.VALIDATE_FAILED,e.getMessage());
        }
        return Result.custom(ResultCode.VALIDATE_FAILED,parameterInvalidResult);
    }



    private List<ParameterInvalidResult> getParameterInvalidResult(Exception e){

        List<ParameterInvalidResult> parameterInvalidItemList;

        List<FieldError> fieldErrorList = null;
        if (e instanceof BindException) {
            fieldErrorList = ((BindException) e).getFieldErrors();
        }
        if (e instanceof MethodArgumentNotValidException) {
            fieldErrorList = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }

        parameterInvalidItemList = Optional.ofNullable(fieldErrorList)
                .map(fieldErrors -> fieldErrors.stream().map(fieldError -> {

                    ParameterInvalidResult parameterInvalidItem = new ParameterInvalidResult();
                    parameterInvalidItem.setFieldName(fieldError.getField());
                    parameterInvalidItem.setMessage(fieldError.getDefaultMessage());
                    return parameterInvalidItem;
                }).collect(Collectors.toList())).orElse(new ArrayList<>());

        return parameterInvalidItemList;
    }



}
