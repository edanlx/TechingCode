package com.example.demo.lesson.grace.front;

import com.example.demo.annotations.TrimNotNull;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrimNotNullImpl implements ConstraintValidator<TrimNotNull, String> {
    @Override
    public void initialize(TrimNotNull constraintAnnotation) {

    }
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNoneBlank(s);
    }
}
