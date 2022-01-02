package com.example.demo.lesson.grace.front;

import com.example.demo.annotations.LogAnnotation;
import com.example.demo.bean.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    FrontTestService FrontTestService;

    @PostMapping("testFront")
    @LogAnnotation(parameter = true, totalConsume = true)
    /**
     * 类写不写无所谓，属性上一定要写
     */
    public ResponseVO<FrontReqVO> testFront(@Validated @RequestBody FrontReqVO frontReqVO) {
        return ResponseVO.<FrontReqVO>builder().data(frontReqVO).build();
    }
    @PostMapping("testFrontPost")
    @LogAnnotation(parameter = true, totalConsume = true)
    public ResponseVO<FrontReqVO> testFrontPost(@Validated FrontReqVO frontReqVO) {
        return ResponseVO.<FrontReqVO>builder().data(frontReqVO).build();
    }

    @GetMapping("testFront2")
    @LogAnnotation(parameter = true, totalConsume = false)
    public ResponseVO<FrontRepVO> testFront2(FrontReqVO frontReqVO) {
        return new ResponseVO<>();
    }

    @PostMapping("testFront3")
    @LogAnnotation(parameter = true, totalConsume = false)
    public ResponseVO<FrontRepVO> testFront3(@RequestBody FrontReqVO frontReqVO) {
        FrontTestService.testFront3Valid(frontReqVO);
        return new ResponseVO<>();
    }
}
