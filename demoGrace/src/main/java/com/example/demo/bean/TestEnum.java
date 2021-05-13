package com.example.demo.bean;

import com.example.demo.lesson.grace.reflect.EnumReflects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author 876651109@qq.com
 * @date 2021/2/9 4:20 下午
 */
@Getter
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
public enum TestEnum {
    /**
     * excel
     */
    EXCEL(1, "excel"),
    PPT(2, "ppt"),
    ;
    /**
     * 编码
     */
    private int code;
    /**
     * 描述
     */
    private String desc;
    public final static Map<Integer, TestEnum> ENUM_CODE = EnumReflects.enumToMap(TestEnum.class, TestEnum::getCode);
    public final static List<Map<String, Object>> ENUM_LIST = EnumReflects.getListMap(TestEnum.class);
}

