package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

public class ContainsCapsLetterRule extends AbstractLetterRule {


    public ContainsCapsLetterRule() {
        super(Character::isUpperCase);
    }

    @Override
    public ResponseCode apply(String cred) {

        boolean hasCaps = checkIfApplicable(cred);

        if (hasCaps) {
            return ResponseCode.SUCCESS;
        } else {
            return ResponseCode.NO_CAPS_CHAR;
        }
    }
}
