package com.example.demo.util.example.apache;

import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;
import org.apache.commons.text.*;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.JaccardSimilarity;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class TextExample {
    public static void main(String[] args) {
        // CaseUtils
        // FormattableUtils
        // StringEscapeUtils
        // WordUtils

        // wordUtilsExample();
        // stringEscapeUtilsExample();
        // jaccardSimilarityExample();
        //  messageFormatExample();
        stringEscapeUtilsExample();
    }

    public static void wordUtilsExample(){
        // 每个单词首字母大写
        System.out.println(WordUtils.capitalize("i am fine"));

        // 每个单词首字母小写
        System.out.println(WordUtils.uncapitalize("I AM FINE"));

        // 取每个单词的首字母
        System.out.println(WordUtils.initials("I AM FINE"));

        // 一行显示X个字符，计算空格
        System.out.println( WordUtils.wrap("i am fine" ,9));

        // 自定义换行符
        System.out.println( WordUtils.wrap("i am fine" ,4,"\n",false));

        // 大小写转换，没什么用
        System.out.println(WordUtils.swapCase("I am Fine"));

        // 是否包含所有单词
        System.out.println(WordUtils.containsAllWords("abc def", "def", "abc"));

        // 没什么用的
        WordUtils.abbreviate("Now is the time for all good men", 0, 40, null);
    }

    public static void stringEscapeUtilsExample(){
        System.out.println(StringEscapeUtils.escapeHtml4("<html></html>"));
        System.out.println(StringEscapeUtils.escapeEcmaScript("<script>alert('123')<script>"));
        System.out.println(StringEscapeUtils.escapeXml11("<html></html>"));
        System.out.println(StringEscapeUtils.escapeJava("你好"));
        System.out.println(StringEscapeUtils.escapeJava("<html></html>"));
    }

    private static void jaccardSimilarityExample() {
        //计算jaccard相似系数
        JaccardSimilarity jaccardSimilarity = new JaccardSimilarity();
        double jcdsimilary1 = jaccardSimilarity.apply("hello", "hell");
        System.out.println("jcdsimilary1:"+jcdsimilary1);
        double jcdsimilary2 = jaccardSimilarity.apply("this is an apple", "this is an app");
        System.out.println("jcdsimilary2:"+jcdsimilary2);
        //计算余弦相似度
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        Map<CharSequence, Integer> leftVector = new HashMap<>();
        Map<CharSequence, Integer> rightVector = new HashMap<>();
        leftVector.put("a", 1);
        leftVector.put("b", 0);
        leftVector.put("c", 1);
        rightVector.put("a", 1);
        rightVector.put("b", 1);
        rightVector.put("c", 0);
        double cosSimilary = cosineSimilarity.cosineSimilarity(leftVector, rightVector);
        System.out.println("cosSimilary:"+cosSimilary);
    }


    private static void messageFormatExample() {
        String param1 = String.format("hi,%s, your age is %s", "john", "26");
        System.out.println("-------------------param1=" + param1);
        Object[] object = new Object[]{"john", Longs.tryParse("24")};
        MessageFormat messageFormat = new MessageFormat("{0} now is at the age of {1}");
        String param2 = messageFormat.format(object);
        System.out.println("-------------------param2=" + param2);

        Map<String, String> replaceValue = Maps.newHashMap();
        replaceValue.put("name", "john");
        replaceValue.put("age", "27");
        StrSubstitutor strSubstitutor = new StrSubstitutor(replaceValue);
        String template1 = "${name} is at the age of${age}";
        String param3 = strSubstitutor.replace(template1);
        System.out.println("-------------------param3=" + param3);

        System.out.println(StringSubstitutor.replace("${name} is at the age of${age}",replaceValue));
    }
}
