package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

import java.util.regex.Pattern;

public class ContainsNumberRule implements Rule {

    private final String NUMBER_MATCHER_PATTERN = ".*\\d.*";
    private final Pattern pattern = Pattern.compile(NUMBER_MATCHER_PATTERN);

    @Override
    public ResponseCode apply(String cred) {
        if (pattern.matcher(cred).matches()) {
            return ResponseCode.SUCCESS;
        }
        return ResponseCode.NO_NUMBER;
    }
}
