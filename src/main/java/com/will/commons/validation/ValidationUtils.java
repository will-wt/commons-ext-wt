package com.will.commons.validation;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证工具类
 * @author Will.WT
 * @date 2022/11/04 16:46
 */
public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    /**
     * 验证对象的限制/约束
     * @param object
     * @param groups
     * @param <T>
     * @return
     */
    public static <T> ValidationResult doValidate(T object, Class<?>... groups){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(object, groups);

        if (CollectionUtils.isNotEmpty(set)){
            Map<String, String> errorMap = new HashMap<String, String>(12);
            for (ConstraintViolation<T> cv : set){
                errorMap.put(cv.getPropertyPath().toString(), cv.getMessage());
            }

            result.setHasError(true);
            result.setErrorMap(errorMap);
        }

        return result;
    }

    /**
     * 验证对象属性的限制/约束
     * @param object
     * @param propertyName
     * @param groups
     * @param <T>
     * @return
     */
    public static <T> ValidationResult doValidate(T object, String propertyName, Class<?>... groups){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(object, propertyName, groups);

        if (CollectionUtils.isNotEmpty(set)){
            Map<String, String> errorMap = new HashMap<String, String>(12);
            for (ConstraintViolation<T> cv : set){
                errorMap.put(propertyName, cv.getMessage());
            }

            result.setHasError(true);
            result.setErrorMap(errorMap);
        }

        return result;
    }


}
