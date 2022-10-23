package com.test.cred.validator.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.model.ValidationResult;
import com.test.cred.rule.Rule;
import com.test.cred.validator.CredValidator;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class SerialCredValidator implements CredValidator {


    private List<Rule> rules;

    public SerialCredValidator(List<Rule> rules) {
        this.rules = rules;
    }


    @Override
    public ValidationResult validate(String cred) {
        ValidationResult result = new ValidationResult();
        for (Rule rule : rules) {
            //check if further processing of rules needed
            if (processMoreRules(result)) {
                ResponseCode responseCode = rule.apply(cred);

                if (ResponseCode.SUCCESS.equals(responseCode)) {
                    result.setSuccessRuleCount(result.getSuccessRuleCount() + 1);
                } else {
                    //if not success then add to response codes
                    result.addToResponseCodes(responseCode);
                }
            } else {
                break;
            }

        }
        processResult(result);
        return result;
    }

    protected void processResult(ValidationResult result) {
        //if all the rules are applied and no error in response codes
        //then it means everything is success
        if (CollectionUtils.isEmpty(result.getResponseCodes())) {
            result.setSuccess(true);
            result.setResponseCodes(Collections.singletonList(ResponseCode.SUCCESS));
        }
    }

    //sub classes can use this method to inform that if further rule processing is needed
    protected boolean processMoreRules(ValidationResult result) {
        return true;
    }
}
