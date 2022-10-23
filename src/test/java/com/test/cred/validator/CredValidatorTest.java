package com.test.cred.validator;


import com.test.cred.model.FailureReason;
import com.test.cred.model.ValidationResult;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class CredValidatorTest {


    @Test
    public void testCredValidator_ValidPassword_1() {
        CredValidator credValidator = new SerialCredValidator();

        ValidationResult result = credValidator.validate("LargerThan8");

        assertTrue(result.isSuccess());
    }

    @Test
    public void testCredValidator_ValidPassword_2() {
        CredValidator credValidator = new SerialCredValidator();

        ValidationResult result = credValidator.validate("LargerThan8#@$%%%");

        assertTrue(result.isSuccess());
    }


    @Test
    public void testCredValidator_InvalidPassword_LessThan8Chars() {
        CredValidator credValidator = new SerialCredValidator();

        ValidationResult result = credValidator.validate("Less8");

        assertFalse(result.isSuccess());
        assertTrue(result.getFailureReasons().contains(FailureReason.LESS_THAN_8_CHAR));
    }

    @Test
    public void testCredValidator_InvalidPassword_NoSmallLetter() {
        CredValidator credValidator = new SerialCredValidator();

        ValidationResult result = credValidator.validate("MY_VERY_BIG_PASSWORD");

        assertFalse(result.isSuccess());
        assertTrue(result.getFailureReasons().contains(FailureReason.NO_SMALL_CHAR));
        assertTrue(result.getFailureReasons().contains(FailureReason.NO_NUMBER));
    }

    @Test
    public void testCredValidator_InvalidPassword_NoCapslLetter() {
        CredValidator credValidator = new SerialCredValidator();

        ValidationResult result = credValidator.validate("another-big-password");

        assertFalse(result.isSuccess());
        assertTrue(result.getFailureReasons().contains(FailureReason.NO_CAPS_CHAR));
        assertTrue(result.getFailureReasons().contains(FailureReason.NO_NUMBER));
    }


}
