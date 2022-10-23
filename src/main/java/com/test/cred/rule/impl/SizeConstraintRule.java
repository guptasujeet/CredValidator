package com.test.cred.rule.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.rule.DelayedProcessingRule;

public class SizeConstraintRule extends DelayedProcessingRule {


    @Override
    public ResponseCode applyRule(String cred) {
        if (cred.length() < 8) {
            return ResponseCode.LESS_THAN_8_CHAR;
        }
        return ResponseCode.SUCCESS;
    }
}
