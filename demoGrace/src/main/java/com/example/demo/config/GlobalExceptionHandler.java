package com.example.demo.config;

import com.example.demo.bean.FieldValidError;
import com.example.demo.bean.ResponseVO;
import enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/3 8:20 PM
 * @description
 */
@Slf4j
@RestControllerAdvice
@Controller
public class GlobalExceptionHandler implements ErrorController {

    /**
     * 404
     *
     * @author seal 876651109@qq.com
     * @date 2020/6/4 1:29 AM
     */
    @RequestMapping(value = "/error")
    @ResponseBody
    public ResponseVO<String> error(HttpServletRequest request, HttpServletResponse response) {
        return ResponseVO.<String>builder().code(ErrorCodeEnum.ADDRESS_NOT_IN_SERVICE.getCode()).msg(ErrorCodeEnum.ADDRESS_NOT_IN_SERVICE.getZhCn()).build();
    }

    /**
     * 其它未定义错误
     *
     * @author seal 876651109@qq.com
     * @date 2020/6/4 12:57 AM
     */
    @ExceptionHandler(Exception.class)
    public ResponseVO<String> handleException(Exception e) {
        log.warn("handleException:" + e.getMessage(), e);
        return ResponseVO.<String>builder().code(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR.getCode()).msg(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR.getZhCn()).build();
    }

    /**
     * 请求方式异常例如get/post
     *
     * @author seal 876651109@qq.com
     * @date 2020/6/4 12:58 AM
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO<String> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseVO.<String>builder().code(ErrorCodeEnum.USER_API_REQUEST_VERSION_MISMATCH.getCode()).msg(ErrorCodeEnum.USER_API_REQUEST_VERSION_MISMATCH.getZhCn()).build();
    }

    /**
     * 重写校验异常，按照统一格式返回
     *
     * @param e
     * @return {@link ResponseVO< List< FieldValidError>>}
     * @author 876651109@qq.com
     * @date 2021/5/14 5:06 下午
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVO<List<FieldValidError>> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResponseVO<List<FieldValidError>> vo = ResponseVO.<List<FieldValidError>>builder().build();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldValidError> list = new ArrayList<>(fieldErrors.size());
        vo.setData(list);
        for (FieldError error : fieldErrors) {
            FieldValidError build = FieldValidError.builder().field(error.getField()).msg(error.getDefaultMessage()).build();
            switch (error.getCode()) {
                case "Size":
                    build.setMsg(String.format("必须在%s到%s之间", error.getArguments()[2], error.getArguments()[1]));
                    break;
                default:
                    break;
            }
            list.add(build);
        }
        return vo;
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
