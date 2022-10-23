package com.test.cred.validator;


import com.test.cred.model.ResponseCode;
import com.test.cred.model.ValidationResult;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CredValidatorTest {


    @Test
    public void testCredValidator_ValidPassword_1() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("LargerThan8");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.SUCCESS));
    }

    @Test
    public void testCredValidator_ValidPassword_2() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("LargerThan8#@$%%%");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.SUCCESS));
    }


    @Test
    public void testCredValidator_InvalidPassword_LessThan8Chars() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("Less8");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.LESS_THAN_8_CHAR));
    }

    @Test
    public void testCredValidator_InvalidPassword_NoSmallLetter() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("MY_VERY_BIG_PASSWORD");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_NUMBER));
    }

    @Test
    public void testCredValidator_InvalidPassword_NoCapslLetter() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("another-big-password");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_CAPS_CHAR));
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_NUMBER));
    }

    @Test
    public void testCredValidator_InvalidPassword_Empty() {
        CredValidator credValidator = new AllRuleSerialCredValidator();

        ValidationResult result = credValidator.validate("");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_CAPS_CHAR));
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_NUMBER));
        assertTrue(result.getResponseCodes().contains(ResponseCode.EMPTY_CREDENTIALS));
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }


}
