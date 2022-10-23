package com.test.cred.validator;

import com.test.cred.model.ValidationResult;

public interface CredValidator {


    ValidationResult validate(String cred);

}
