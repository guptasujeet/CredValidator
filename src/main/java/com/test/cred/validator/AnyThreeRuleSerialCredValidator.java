package com.test.cred.validator;

import com.test.cred.model.ResponseCode;
import com.test.cred.model.ValidationResult;

import java.util.Collections;

public class AnyThreeRuleSerialCredValidator extends AllRuleSerialCredValidator {

    @Override
    protected boolean processMoreRules(ValidationResult result) {
        return result.getSuccessRuleCount() < 3;
    }

    @Override
    protected void processResult(ValidationResult result) {
        if (result.getSuccessRuleCount() >= 3) {
            result.setSuccess(true);
            result.setResponseCodes(Collections.singletonList(ResponseCode.SUCCESS));
        } else {
            super.processResult(result);
        }
    }
}
