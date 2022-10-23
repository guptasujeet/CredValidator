package com.test.cred.rule;

import com.test.cred.model.ResponseCode;
import org.apache.commons.lang3.StringUtils;

public class NotNullRule implements Rule {

    @Override
    public ResponseCode apply(String cred) {
        if (StringUtils.isAllBlank(cred)) {
            return ResponseCode.EMPTY_CREDENTIALS;
        }
        return ResponseCode.SUCCESS;
    }
}
