package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

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
