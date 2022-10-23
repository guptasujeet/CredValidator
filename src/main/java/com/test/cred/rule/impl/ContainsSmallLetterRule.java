package com.test.cred.rule.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.rule.AbstractLetterRule;

public class ContainsSmallLetterRule extends AbstractLetterRule {


    public ContainsSmallLetterRule() {
        super(Character::isLowerCase);
    }

    @Override
    public ResponseCode apply(String cred) {

        boolean hasCaps = checkIfApplicable(cred);

        if (hasCaps) {
            return ResponseCode.SUCCESS;
        } else {
            return ResponseCode.NO_SMALL_CHAR;
        }
    }
}
