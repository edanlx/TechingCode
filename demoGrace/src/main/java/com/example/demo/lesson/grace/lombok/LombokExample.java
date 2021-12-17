package com.example.demo.lesson.grace.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class LombokExample {

    @Data
    static class DataBean {
        private String name;
    }

    public static void DataBeanExample() {
        log.info(new DataBean().getName());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    static class ConstructorBean {
        private String name1;
        private String name2;
    }

    public static void ConstructorExample() {
        log.info(new ConstructorBean("1", "2").toString());
    }

    @Builder
    @ToString
    static class BuilderBean {
        private String name1;
        private String name2;
    }

    public static void BuilderExample() {
        log.info(BuilderBean.builder().name1("1").name2("2").build().toString());
    }

    @Accessors(chain = true)
    @Setter
    @ToString
    static class ChainBean {
        private String name1;
        private String name2;
    }

    public static void ChainExample() {
        log.info(new ChainBean().setName1("1").setName2("2").toString());
    }

    @SneakyThrows({IOException.class})
    public static void ExceptionExample() {
        CloseExample();
    }

    public static void CloseExample() throws FileNotFoundException {
        try {
            @Cleanup FileInputStream fileInputStream = new FileInputStream("");
        } catch (IOException e) {

        }
    }

    public static void CloseExample2() {
        try (FileInputStream fileInputStream = new FileInputStream("")) {
            log.info("123");
        } catch (IOException e) {

        }
    }

    public static void main(String[] args) {
        DataBeanExample();
        ConstructorExample();
        BuilderExample();
        ChainExample();
        ExceptionExample();
    }

}
