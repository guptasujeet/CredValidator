package com.test.cred.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResult {

    private boolean success;

    //check failure reason only if success is false
    private List<ResponseCode> responseCodes;
    private int successRuleCount;

    public void addToResponseCodes(ResponseCode responseCode) {
        if (responseCodes == null) {
            responseCodes = new ArrayList<>();
        }
        responseCodes.add(responseCode);
    }

}
