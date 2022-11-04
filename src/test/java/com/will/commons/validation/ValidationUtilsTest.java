package com.will.commons.validation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Will.WT
 * @date 2022/11/04 21:02
 */
public class ValidationUtilsTest {

    @Test
    public void testHasError(){
        UserTmp user = new UserTmp();
        ValidationResult result = ValidationUtils.doValidate(user);
        assertTrue(result.isHasError());
        assertEquals(result.getErrorMap().size(), 2);

        user.setUserId(10001L);
        result = ValidationUtils.doValidate(user);
        assertTrue(result.isHasError());
        assertEquals(result.getErrorMap().size(), 1);

        user.setName("张");
        result = ValidationUtils.doValidate(user);
        assertTrue(result.isHasError());
        assertEquals(result.getErrorMap().size(), 1);
        assertEquals(result.getErrorMap().keySet().iterator().next(), "name");


        user.setName("张三");
        user.setGender(3);

        result = ValidationUtils.doValidate(user);
        assertTrue(result.isHasError());
        assertEquals(result.getErrorMap().size(), 1);
        assertEquals(result.getErrorMap().keySet().iterator().next(), "gender");
    }


    @Test
    public void testNoError(){
        UserTmp user = new UserTmp();
        user.setUserId(10001L);
        user.setName("张三");
        user.setGender(1);
        user.setAge(20);

        ValidationResult result = ValidationUtils.doValidate(user);
        assertNotNull(result);
        assertFalse(result.isHasError());
    }

}
