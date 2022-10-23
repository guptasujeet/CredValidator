package com.test.cred.rule.impl;

import com.test.cred.model.ResponseCode;
import com.test.cred.rule.AbstractLetterRule;

public class ContainsCapsLetterRule extends AbstractLetterRule {


    public ContainsCapsLetterRule() {
        super(Character::isUpperCase);
    }

    @Override
    public ResponseCode applyRule(String cred) {

        boolean hasCaps = checkIfApplicable(cred);

        if (hasCaps) {
            return ResponseCode.SUCCESS;
        } else {
            return ResponseCode.NO_CAPS_CHAR;
        }
    }
}
