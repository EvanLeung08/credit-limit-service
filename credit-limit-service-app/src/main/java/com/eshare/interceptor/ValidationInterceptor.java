package com.eshare.interceptor;

import com.alibaba.cola.command.CommandInterceptorI;
import com.alibaba.cola.command.PreInterceptor;
import com.alibaba.cola.dto.ClientObject;
import com.alibaba.cola.dto.Command;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.validator.ColaMessageInterpolator;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * ValidationInterceptor
 *
 * @author Evan Leung 2018-01-06 8:27 PM
 */
@PreInterceptor
public class ValidationInterceptor implements CommandInterceptorI {

    //Enable fail fast, which will improve performance
    private ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
            .messageInterpolator(new ColaMessageInterpolator()).buildValidatorFactory();

    @Override
    public void preIntercept(Command command) {
        Validator validator = factory.getValidator();
        //对命令对象进行校验
        try {
            validate(command, validator);
        } catch (IllegalAccessException e) {
            throw new BizException(e.getMessage(), e);
        }

    }

    /**
     * 递归进行属性校验
     * @param obj
     * @param validator
     * @throws IllegalAccessException
     */
    private void validate(Object obj, Validator validator) throws IllegalAccessException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        constraintViolations.forEach(violation -> {
            throw new BizException(violation.getPropertyPath() + " " + violation.getMessage());
        });
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            Object fObj = f.get(obj);
            //如果属性非clientObject 直接终止
            if (!(fObj instanceof ClientObject)) {
                return;
            }
            validate(fObj, validator);
        }

    }
}
