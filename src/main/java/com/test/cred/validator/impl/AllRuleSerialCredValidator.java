package com.test.cred.validator.impl;

import com.google.common.collect.Lists;
import com.test.cred.rule.impl.*;

public class AllRuleSerialCredValidator extends SerialCredValidator {

    public AllRuleSerialCredValidator() {
        super(
                Lists.newArrayList(
                        new NotNullRule(),
                        new SizeConstraintRule(),
                        new ContainsCapsLetterRule(),
                        new ContainsSmallLetterRule(),
                        new ContainsNumberRule()
                )
        );
    }
}
