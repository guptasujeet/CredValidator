package com.test.cred.validator;

import com.google.common.collect.Lists;
import com.test.cred.model.ResponseCode;
import com.test.cred.model.ValidationResult;
import com.test.cred.rule.Rule;
import com.test.cred.rule.impl.ContainsSmallLetterRule;
import com.test.cred.validator.impl.AnyThreeRuleSerialCredValidator;
import com.test.cred.validator.impl.ParallelCustomCredValidator;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ParallelCustomCredValidatorTest {

    private final List<Rule> mandatoryRule = Lists.newArrayList(new ContainsSmallLetterRule());
    private final CredValidator nextValidator = new AnyThreeRuleSerialCredValidator();

    @Test
    public void testCredValidator_ValidPassword_Caps_Small_NotNull() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("Lar");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.SUCCESS));
    }

    @Test
    public void testCredValidator_ValidPassword_Caps_Small_Number() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("LargerThan8#@$%%%");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.SUCCESS));
    }


    @Test
    public void testCredValidator_InvalidPassword_Number_Caps_NotNull() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("L2");

        assertFalse(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }

    @Test
    public void testCredValidator_ValidPassword_Number_Small_NotNull() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("a1");

        assertTrue(result.isSuccess());
        assertEquals(1, result.getResponseCodes().size());
        assertTrue(result.getResponseCodes().contains(ResponseCode.SUCCESS));
    }

    @Test
    public void testCredValidator_InvalidPassword_LessThan8Chars() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("2");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }

    @Test
    public void testCredValidator_ValidPassword_NoSmallLetter() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("MY_VERY_BIG_PASSWORD");

        //no small letter
        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }

    @Test
    public void testCredValidator_InvalidPassword_NoCapslLetter() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("an");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_CAPS_CHAR));
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_NUMBER));
    }

    @Test
    public void testCredValidator_InvalidPassword_Empty() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate("");

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }

    @Test
    public void testCredValidator_InvalidPassword_NULL() {
        CredValidator credValidator = new ParallelCustomCredValidator(mandatoryRule, nextValidator);

        ValidationResult result = credValidator.validate(null);

        assertFalse(result.isSuccess());
        assertTrue(result.getResponseCodes().contains(ResponseCode.NO_SMALL_CHAR));
    }

}
