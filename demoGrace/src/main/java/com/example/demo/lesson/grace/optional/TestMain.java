package com.example.demo.lesson.grace.optional;

import lombok.NonNull;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/9/1 9:31 AM
 * @description
 */
public class TestMain {
    public static void main(String[] args) {
        // nullTest(null);

        GrandParent opt1 = null;
        String opt1Str =
                Optional.ofNullable(opt1).map(o1 -> o1.getParent())
                        .map(o2 -> o2.getChild()).map(l->l.getStr()).orElse(null);
        System.out.println(String.format("%s:%s", "opt1Object", opt1Str));

        GrandParent opt2 = null;
        List<String> opt2list =
                Optional.ofNullable(opt2).map(o1 -> o1.getParent())
                        .map(o2 -> o2.getChild()).map(l->l.getList()).orElse(null);
        System.out.println(String.format("%s:%s", "opt2list", opt2list));

        GrandParent opt3 = new GrandParent().setParent(new Parent().setChild(new Child().setStr("ssss").setList(Stream.of("1", "2").collect(Collectors.toList()))));
        List<String> opt3list =
                Optional.ofNullable(opt3).map(o1 -> o1.getParent())
                        .map(o2 -> o2.getChild()).map(l->l.getList()).orElse(null);
        String opt3Str =
                Optional.ofNullable(opt3).map(o1 -> o1.getParent())
                        .map(o2 -> o2.getChild()).map(l->l.getStr()).orElse(null);
        System.out.println(String.format("%s:%s", "opt3list", opt3list));
        System.out.println(String.format("%s:%s", "opt3Str", opt3Str));

        System.out.println(String.format("%s:%s", "StringUtils", StringUtils.isBlank(null)));
        System.out.println(String.format("%s:%s", "defaultIfNull", ObjectUtils.defaultIfNull(null, "defaultIfNull")));
        List list = null;
        Map map = null;
        Set set = null;
        String[] arr = null;
        System.out.println(String.format("%s:%s", "list", CollectionUtils.isEmpty(list)));
        System.out.println(String.format("%s:%s", "map", CollectionUtils.isEmpty(map)));
        System.out.println(String.format("%s:%s", "set", CollectionUtils.isEmpty(set)));
        System.out.println(String.format("%s:%s", "arr", ArrayUtils.isEmpty(arr)));

        List<Map<String, String>> listR = null;
        String result = Optional.ofNullable(listR).flatMap(l -> l.stream().findAny())
                .flatMap(l -> l.keySet().stream().findAny()).orElse(null);
        System.out.println(result);
        listR = new ArrayList<Map<String, String>>();
        result = Optional.ofNullable(listR).flatMap(l -> l.stream().findAny())
                .flatMap(l -> l.keySet().stream().findAny()).orElse(null);
        System.out.println(result);
        listR = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>());
        }};
        result = Optional.ofNullable(listR).flatMap(l -> l.stream().findAny())
                .flatMap(l -> l.keySet().stream().findAny()).orElse(null);
        System.out.println(result);
        listR = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("C", "0");
            }});
        }};
        result = Optional.ofNullable(listR).flatMap(l -> l.stream().findAny())
                .flatMap(l -> l.keySet().stream().findAny()).orElse(null);
        System.out.println(result);
    }

    public static void nullTest(@NonNull String str) {

    }
}
