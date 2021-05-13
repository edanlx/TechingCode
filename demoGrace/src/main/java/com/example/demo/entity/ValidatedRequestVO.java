package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/** 
* demo
* @author seal 876651109@qq.com
* @date 6/28/2020 9:44 PM
*/
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Data
public class ValidatedRequestVO implements Serializable {
    private static final long serialVersionUID = -4362139990889081422L;
    private String str;
    @Nullable
    private Date startDate;
    private LocalDateTime endDate;
    private Date beginDate;
}
