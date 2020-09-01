package com.example.demo.lesson.grace.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/9/1 9:21 AM
 * @description
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
public class GrandParent {
    private Parent parent;
}
