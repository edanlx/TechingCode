package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TestTreeObj {
    private int id;
    private int pid;
    private List<TestTreeObj> testTreeObj;
}
