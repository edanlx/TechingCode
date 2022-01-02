package com.example.demo.lesson.grace.front;

import com.example.demo.annotations.TrimNotNull;
import com.example.demo.bean.Insert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Date;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FrontReqVO {
    @NotNull
    private String name;
    @Size(min = 1)
    private String name2;
    // 如果加了其它分组要把default带上，不然会覆盖
    // 校验去除空格后不可为空
    @TrimNotNull(groups = {Default.class, Insert.class})
    // 序列化,去除空格
    @JsonSerialize(using = TrimNotNullJson.class)
    // 反序列化，去除空格
    @JsonDeserialize(using = TrimNotNullDeJson.class)
    private String name3;
    @Pattern(regexp = "^[Y|N]$")
    private String yesOrNo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    private ErrorCodeEnum errorCodeEnum;
    private Boolean boo;
}
