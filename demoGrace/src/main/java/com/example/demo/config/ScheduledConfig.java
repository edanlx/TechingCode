package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/4 7:42 PM
 * @description
 */
@Slf4j
@EnableScheduling
@Configuration
public class ScheduledConfig {
//    @Scheduled(cron = "0 0/5 * * * ?")
    public void Check(){
        log.info(LocalDateTime.now().toString());
    }
}
