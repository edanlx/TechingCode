package com.example.demo.util;

import com.example.demo.entity.ValidatedRequestVO;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** 
* @description  
* @author seal 876651109@qq.com
* @date 6/28/2020 10:16 PM
* @param  
* @return  
*/
@Slf4j
public class FileUtil {
    public static void read() throws FileNotFoundException {
        try {
            @Cleanup FileInputStream fis = new FileInputStream("");
            ValidatedRequestVO.builder().str("123").build();
        }catch (IOException e){
            e.printStackTrace();
            log.warn("read:", e);
        }
    }
}
