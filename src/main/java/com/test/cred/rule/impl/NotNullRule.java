package com.test.cred.rule.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.rule.DelayedProcessingRule;
import org.apache.commons.lang3.StringUtils;

public class NotNullRule extends DelayedProcessingRule {

    @Override
    public ResponseCode applyRule(String cred) {
        if (StringUtils.isAllBlank(cred)) {
            return ResponseCode.EMPTY_CREDENTIALS;
        }
        return ResponseCode.SUCCESS;
    }
}
