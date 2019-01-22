package com.koubeisi.validate;

import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/22 12:00
 * @Version 1.0
 **/
@Component
public class ValidatorUtil {

    private Validator validator = Validation.byProvider(HibernateValidator.class).
            configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 实体校验
     * @param bean
     */
    public <T> ValidatorResult validate(T bean) {

        ValidatorResult validatorResult = new ValidatorResult();

        //验证bean是否符合规范，返回错误信息集合set
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(bean);
        //集合set大于零表示有错误
        if (constraintViolations.size() > 0) {

            validatorResult.setHasError(true);

            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();

            while (iterator.hasNext()){
                ConstraintViolation constraintViolation = iterator.next();
                //获取错误的信息
                String errorMsg = constraintViolation.getMessage();
                //获取错误的属性名
                String property = constraintViolation.getPropertyPath().toString();

                validatorResult.getErrorMsgMap().put(property, errorMsg);
            }
        }

        return validatorResult;
    }

}
