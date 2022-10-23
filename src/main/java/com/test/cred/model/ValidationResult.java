package com.test.cred.model;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResult {

    private boolean success;

    //check failure reason only if success is false
    private List<FailureReason> failureReasons;

}
