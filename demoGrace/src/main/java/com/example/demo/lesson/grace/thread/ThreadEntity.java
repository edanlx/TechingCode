package com.example.demo.lesson.grace.thread;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ThreadEntity {
    private int num;
    private int price;

    public int countPrice() {
        price = RandomUtils.nextInt();
        try {
            System.out.println("start" + num);
            // 随机等待1~10秒
            Thread.sleep(RandomUtils.nextInt(1, 10) * 1000);
            System.out.println("end" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return price;
    }
}
