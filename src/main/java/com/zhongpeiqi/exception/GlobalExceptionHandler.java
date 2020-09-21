package com.zhongpeiqi.exception;

import com.google.common.collect.ImmutableMap;
import com.zhongpeiqi.common.code.CommonCode;
import com.zhongpeiqi.common.code.ResultCode;
import com.zhongpeiqi.common.response.Response;
import com.zhongpeiqi.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;

    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static {
        builder.put(AuthenticationException.class, CommonCode.AUTHENTICATE_FAIL);
        builder.put(IncorrectCredentialsException.class, CommonCode.AUTHENTICATE_FAIL);
        builder.put(UnknownAccountException.class, CommonCode.AUTHENTICATE_FAIL);
        builder.put(UnauthorizedException.class, CommonCode.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomException.class)
    public Response customException(CustomException customException) {
        ResultCode resultCode = customException.getResultCode();
        log.error("catch customException: {}", customException.getMessage());
        customException.printStackTrace();
        return ResponseResult.FAIL(resultCode);
    }

    @ExceptionHandler(BindException.class)
    public Response bindException(BindException bindException) {
       return notValidExceptionHandler(bindException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response bindException(MethodArgumentNotValidException methodArgumentNotValidException) {
       return notValidExceptionHandler(methodArgumentNotValidException);
    }

    @ExceptionHandler(Exception.class)
    public Response exception(Exception e) {
        log.error("catch exception: {}", e.getMessage());
        e.printStackTrace();

        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }

        ResultCode resultCode = EXCEPTIONS.get(e.getClass());

        if (resultCode != null) {
            return ResponseResult.FAIL(resultCode);
        } else {
            return ResponseResult.FAIL(CommonCode.OTHER_EXCEPTION);
        }

    }

    private Response notValidExceptionHandler(Exception e) {
        BindingResult result;
        if (e instanceof BindException) {
            result = ((BindException) e).getBindingResult();
        } else {
            result = ((MethodArgumentNotValidException)e).getBindingResult();
        }

        Map<String, Object> map;
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            map = new HashMap<>(fieldErrors.size());
            fieldErrors.forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
        } else {
            map = Collections.emptyMap();
        }
        return ResponseResult.FAIL("1", "20001", "请求参数错误", map);
    }
}
