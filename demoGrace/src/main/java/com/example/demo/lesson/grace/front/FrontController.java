package com.example.demo.lesson.grace.front;

import com.example.demo.annotations.LogAnnotation;
import com.example.demo.bean.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("frontDemo")
@LogAnnotation
public class FrontController {

    @PostMapping("testFront")
    @LogAnnotation(parameter = true, totalConsume = false)
    public ResponseVO<FrontRepVO> testFront(@Validated @RequestBody FrontReqVO frontReqVO) {
        return new ResponseVO<>();
    }

    @GetMapping("testFront2")
    @LogAnnotation(parameter = true, totalConsume = false)
    public ResponseVO<FrontRepVO> testFront2(FrontReqVO frontReqVO) {
        return new ResponseVO<>();
    }
}
