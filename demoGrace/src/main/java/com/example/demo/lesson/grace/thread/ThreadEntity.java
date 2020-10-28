package com.example.demo.lesson.grace.thread;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/5/31 3:28 PM
 * @description
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ThreadEntity {
    private int num;
    public int countPrice(){
        int a = RandomUtils.nextInt();
        try {
            Thread.sleep(RandomUtils.nextInt(1, 10) * 1000);
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a;
    }
}
