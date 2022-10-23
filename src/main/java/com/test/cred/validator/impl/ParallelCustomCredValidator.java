package com.test.cred.validator.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.model.ValidationResult;
import com.test.cred.rule.Rule;
import com.test.cred.validator.CredValidator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelCustomCredValidator extends CustomCredValidator {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public ParallelCustomCredValidator(List<Rule> mandatoryRules, CredValidator nextValidator) {
        super(mandatoryRules, nextValidator);
    }


    @Override
    public ValidationResult validate(String cred) {
        Future<ValidationResult> mandatoryResFuture = executorService.submit(() -> getMandatoryValidationResult(cred));
        Future<ValidationResult> nextResFuture = executorService.submit(() -> nextValidatorResult(cred));

        return mergeResult(mandatoryResFuture, nextResFuture);
    }


    private ValidationResult getMandatoryValidationResult(String cred) {
        SerialCredValidator mandatoryValidator = new SerialCredValidator(mandatoryRules);
        return mandatoryValidator.validate(cred);
    }

    private ValidationResult nextValidatorResult(String cred) {
        return nextValidator.validate(cred);
    }

    private ValidationResult mergeResult(Future<ValidationResult> mandatoryResFuture, Future<ValidationResult> nextResFuture) {
        try {
            ValidationResult mandatoryValResult = mandatoryResFuture.get();
            if (!mandatoryValResult.isSuccess()) {
                return mandatoryValResult;
            }
            return nextResFuture.get();
        } catch (Exception e) {
            ValidationResult result = new ValidationResult();
            result.addToResponseCodes(ResponseCode.SERVER_ERROR);
            return result;
        }
    }

}
