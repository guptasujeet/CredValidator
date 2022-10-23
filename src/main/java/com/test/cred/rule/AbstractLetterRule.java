package com.test.cred.rule;

import java.util.function.Predicate;

public abstract class AbstractLetterRule implements Rule {


    private final Predicate<Character> predicate;

    AbstractLetterRule(Predicate<Character> predicate) {
        this.predicate = predicate;
    }

    protected boolean checkIfApplicable(String cred) {

        boolean isApplicable = false;

        for (int i = 0; i < cred.length(); i++) {
            if (predicate.test(cred.charAt(i))) {
                isApplicable = true;
                break;
            }
        }

        return isApplicable;

    }


}
