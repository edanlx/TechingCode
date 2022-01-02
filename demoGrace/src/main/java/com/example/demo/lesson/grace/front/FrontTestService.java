package com.example.demo.lesson.grace.front;

import com.example.demo.bean.ResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class FrontTestService {

    public ResponseVO<FrontRepVO> testFront3Valid(@Valid FrontReqVO frontReqVO) {
        return new ResponseVO<>();
    }
}
