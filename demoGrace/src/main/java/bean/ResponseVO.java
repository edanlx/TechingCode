package bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/5/17 11:07 PM
 * @description
 */
//父级使用@Data 可同时体现在ResponseBody

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseVO<T> {
    private T data;
    private String code;
    private String msg;
    public ResponseVO(){
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.msg = ErrorCodeEnum.SUCCESS.getZhCn();
    }
}