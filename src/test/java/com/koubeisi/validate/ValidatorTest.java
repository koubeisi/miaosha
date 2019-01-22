package com.koubeisi.validate;

import com.koubeisi.service.model.UserModel;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Iterator;
import java.util.Set;

public class ValidatorTest {

    private Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();

    /**
     * 实体校验
     * @param obj
     */
    public <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        if (constraintViolations.size() > 0) {
            //ConstraintViolation<T> validateInfo = (ConstraintViolation<T>) constraintViolations.iterator().next();

            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();

            while (iterator.hasNext()){
                ConstraintViolation constraintViolation = iterator.next();

                System.out.println(constraintViolation.getMessage());
                System.out.println(constraintViolation.getPropertyPath());
//                System.out.println(constraintViolation.getMessageTemplate());
//                System.out.println(constraintViolation.getRootBeanClass());
            }

        }
    }

    @Test
    public void test() {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setName("Tom");
        userModel.setGender(new Byte("1"));
//        userModel.setAge(21);
//        userModel.setTelephone("13312345678");
//        userModel.setEncrptPassword("1adfagaefasd");

        validate(userModel);
    }

}