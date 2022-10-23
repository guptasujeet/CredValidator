package com.test.cred.validator.impl;

import com.test.cred.model.ValidationResult;
import com.test.cred.rule.Rule;
import com.test.cred.validator.CredValidator;

import java.util.List;

public class CustomCredValidator implements CredValidator {


    protected final List<Rule> mandatoryRules;
    protected final CredValidator nextValidator;

    public CustomCredValidator(List<Rule> mandatoryRules, CredValidator nextValidator) {
        this.mandatoryRules = mandatoryRules;
        this.nextValidator = nextValidator;
    }

    @Override
    public ValidationResult validate(String cred) {
        SerialCredValidator mandatoryValidator = new SerialCredValidator(mandatoryRules);
        ValidationResult mandatoryValidationResult = mandatoryValidator.validate(cred);
        if (!mandatoryValidationResult.isSuccess()) {
            return mandatoryValidationResult;
        }

        return nextValidator.validate(cred);
    }
}
