package enums;

import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/5/29 1:12 AM
 * @description
 */
public enum DateEnum {
    /** 日期格式 <code>[yyyy-MM-dd]</code> */
    DATE("yyyy-MM-dd"),
    DATE_SHORT_ZH("yyyy年M月d日"),
    DATE_LONG_ZH("yyyy年MM月dd日"),
    DATE_SLASH("yyyy/MM/dd"),
    /** 日期格式 <code>[yyyyMMdd]</code> */
    DATE_COMPACT("yyyyMMdd"),

    /** 日期格式 <code>[yyyy_MM_dd]</code> */
    DATE_UNDERLINE("yyyy_MM_dd"),

    /** 时间格式 <code>[HH:mm:ss]</code> */
    TIME("HH:mm:ss"),

    /** 时间格式 <code>[HHmmss]</code> */
    TIME_COMPACT("HHmmss"),

    /** 时间格式 <code>[HH_mm_ss]</code> */
    TIME_UNDERLINE("HH_mm_ss"),

    /** 时间格式 <code>[HH:mm:ss.SSS]</code> */
    TIME_MILLI("HH:mm:ss.SSS"),

    /** 时间格式 <code>[HHmmssSSS]</code> */
    TIME_MILLI_COMPACT("HHmmssSSS"),

    /** 时间格式 <code>[HH_mm_ss_SSS]</code> */
    TIME_MILLI_UNDERLINE("HH_mm_ss_SSS"),

    /** 日期时间格式 <code>[yyyy-MM-dd HH:mm:ss]</code> */
    DATE_TIME("yyyy-MM-dd HH:mm:ss"),

    /** 日期时间格式 <code>[yyyyMMddHHmmss]</code> */
    DATE_TIME_COMPACT("yyyyMMddHHmmss"),

    /** 日期时间格式 <code>[yyyy_MM_dd_HH_mm_ss]</code> */
    DATE_TIME_UNDERLINE("yyyy_MM_dd_HH_mm_ss"),

    /** 日期时间格式 <code>[yyyy-MM-dd HH:mm:ss.SSS]</code> */
    DATE_TIME_MILLI("yyyy-MM-dd HH:mm:ss.SSS"),

    /** 日期时间格式 <code>[yyyyMMddHHmmssSSS]</code> */
    DATE_TIME_MILLI_COMPACT("yyyyMMddHHmmssSSS"),

    /** 日期时间格式 <code>[yyyy_MM_dd_HH_mm_ss_SSS]</code> */
    DATE_TIME_MILLI_UNDERLINE("yyyy_MM_dd_HH_mm_ss_SSS"),

    DATE_JAVA("EEE MMM dd HH:mm:ss z yyyy");

    private String pattern;
    private static final String[] patterns;

    static {
        List<String> list = Arrays.asList(DateEnum.values()).stream().map(x->x.pattern).collect(Collectors.toList());
        patterns = list.toArray(new String[list.size()]);
    }
    DateEnum(String pattern) {
        this.pattern = pattern;
    }

    @SneakyThrows
    public static Date getDate(String target) {
        return DateUtils.parseDate(target, patterns);
    }
}
