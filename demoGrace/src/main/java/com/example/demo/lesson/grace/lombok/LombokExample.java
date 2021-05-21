package com.example.demo.lesson.grace.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;

@Data
@EqualsAndHashCode()
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Slf4j
public class LombokExample {

    @SneakyThrows({Exception.class})
    public static void main(String[] args) {
        new DataExample();
        new DataExample("");
        DataExample.builder().name("123").build();
        log.info("123");
        @Cleanup FileInputStream fileInputStream = new FileInputStream("");
    }

    @Data
    @EqualsAndHashCode()
    @AllArgsConstructor
    @ToString(callSuper = true)
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    static class DataExample {
        private String name;
    }
}
