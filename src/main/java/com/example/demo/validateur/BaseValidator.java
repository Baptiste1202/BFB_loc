package com.example.demo.validateur;

public class BaseValidator implements Validator {
    private Validator next;

    @Override
    public boolean validate(String input) {
        if (next != null) {
            return next.validate(input);
        }
        return true;
    }

    @Override
    public void setNext(Validator next) {
        this.next = next;
    }

    protected boolean validateNext(String input) {
        if (next != null) {
            return next.validate(input);
        }
        return true;
    }
}
