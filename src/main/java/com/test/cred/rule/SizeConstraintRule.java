package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

public class SizeConstraintRule implements Rule {


    @Override
    public ResponseCode apply(String cred) {
        if (cred.length() < 8) {
            return ResponseCode.LESS_THAN_8_CHAR;
        }
        return ResponseCode.SUCCESS;
    }
}
