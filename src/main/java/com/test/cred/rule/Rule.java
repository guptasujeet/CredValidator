package com.test.cred.rule;

import com.test.cred.model.ResponseCode;

public interface Rule {

    ResponseCode apply(String cred);

}
