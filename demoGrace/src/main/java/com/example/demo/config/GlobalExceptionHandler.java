package com.example.demo.config;

import com.example.demo.bean.ResponseVO;
import enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @author seal 876651109@qq.com
     * @date 2020/6/4 1:29 AM
     */
    @RequestMapping(value = "/error")
    @ResponseBody
    public ResponseVO<String> error(HttpServletRequest request, HttpServletResponse response){
        return ResponseVO.<String>builder().code(ErrorCodeEnum.ADDRESS_NOT_IN_SERVICE.getCode()).msg(ErrorCodeEnum.ADDRESS_NOT_IN_SERVICE.getZhCn()).build();
    }

    /**
    * 其它未定义错误
    * @author seal 876651109@qq.com
    * @date 2020/6/4 12:57 AM
    */
    @ExceptionHandler(Exception.class)
    public ResponseVO<String> handleException(Exception e){
        log.warn("handleException:" + e.getMessage(), e);
        return ResponseVO.<String>builder().code(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR.getCode()).msg(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR.getZhCn()).build();
    }

    /**
    * 请求方式异常例如get/post
    * @author seal 876651109@qq.com
    * @date 2020/6/4 12:58 AM
    */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO<String> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return ResponseVO.<String>builder().code(ErrorCodeEnum.USER_API_REQUEST_VERSION_MISMATCH.getCode()).msg(ErrorCodeEnum.USER_API_REQUEST_VERSION_MISMATCH.getZhCn()).build();
    }
    /**
    * json解析异常
    * @author seal 876651109@qq.com
    * @date 2020/6/4 1:39 AM
     *
     * 有如下情况
     * JSON parse error: Cannot deserialize value of type `java.util.Date` from String "2020": expected format "yyyy/MM"; nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.util.Date` from String "2020": expected format "yyyy/MM"
     *  at [Source: (PushbackInputStream); line: 6, column: 15] (through reference chain: com.example.demo.entity.ValidatedRequestVO["startDate"])
     *
     *  JSON parse error: Cannot deserialize value of type `java.time.LocalDateTime` from String "2020/05/1": Failed to deserialize java.time.LocalDateTime: (java.time.format.DateTimeParseException) Text '2020/05/1' could not be parsed at index 8
    */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseVO<String> HttpMessageNotReadableException(HttpMessageNotReadableException e) throws ClassNotFoundException, NoSuchFieldException {
//        String regClass = "reference chain: (.*?)\\[";
//        Matcher classMatcher = Pattern.compile(regClass).matcher(e.getMessage());
//        classMatcher.find();
//        Class clazz = Class.forName(classMatcher.group(1));
//
//        String fieldExpect = "\\[\"(.*?)\"\\]";
//        Matcher fieldMatcher = Pattern.compile(fieldExpect).matcher(e.getMessage());
//        fieldMatcher.find();
//        Field field = clazz.getDeclaredField(fieldMatcher.group(1));
//
//        ResponseVO vo = ErrorCodeEnum.getResponse(ErrorCodeConstant.USER_REQUEST_PARAMETER_ERROR);
//        return vo.setMsg(vo.getMsg() + field.getAnnotation(JsonFormat.class).pattern()).setData(field.getName());
//    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseVO<String> MethodArgumentNotValidException(MethodArgumentNotValidException e){
//        FieldError error = e.getBindingResult().getFieldError();
//        ResponseVO vo  = ErrorCodeEnum.getResponse(error.getDefaultMessage()).setData(error.getField());
//        switch (error.getCode()){
//            case "Size":
//                vo.setMsg(vo.getMsg() + String.format("%s-%s", error.getArguments()[2], error.getArguments()[1]));
//                break;
//            default:
//                break;
//        }
//        return vo;
//    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
