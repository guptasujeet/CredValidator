package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

public abstract class DelayedProcessingRule implements Rule {

    @Override
    public ResponseCode apply(String cred) {
        //simulating sleep of 1 sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //do nothing
        }
        return applyRule(cred);
    }

    protected abstract ResponseCode applyRule(String cred);
}
