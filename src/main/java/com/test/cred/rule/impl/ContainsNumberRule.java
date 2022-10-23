package com.test.cred.rule.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.rule.DelayedProcessingRule;

import java.util.regex.Pattern;

// number rule can also be implemented by extending AbstractLetterRule
public class ContainsNumberRule extends DelayedProcessingRule {

    private final String NUMBER_MATCHER_PATTERN = ".*\\d.*";
    private final Pattern pattern = Pattern.compile(NUMBER_MATCHER_PATTERN);

    @Override
    public ResponseCode applyRule(String cred) {
        if (pattern.matcher(cred).matches()) {
            return ResponseCode.SUCCESS;
        }
        return ResponseCode.NO_NUMBER;
    }
}
