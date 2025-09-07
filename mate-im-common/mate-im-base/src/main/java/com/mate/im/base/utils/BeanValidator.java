package com.mate.im.base.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;

import java.util.Set;

/**
 * 参数校验工具
 *
 * @author yuanxmo
 */
public class BeanValidator {

    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();

    /**
     * @param object
     * @param groups
     * @throws ValidationException
     */
    public static void validateObject(Object object, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        if (violations.stream().findFirst().isPresent()) {
            throw new ValidationException(violations.stream().findFirst().get().getMessage());
        }
    }
}
