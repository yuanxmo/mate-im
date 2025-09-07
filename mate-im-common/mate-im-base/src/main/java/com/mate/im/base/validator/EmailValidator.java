package com.mate.im.base.validator;

import cn.hutool.core.lang.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 邮箱校验器
 *
 * @author yuanxmo
 */
public class EmailValidator implements ConstraintValidator<IsEmail, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Validator.isEmail(s);
    }
}
