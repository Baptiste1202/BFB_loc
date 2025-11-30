package com.example.demo.validateur;

public class NotEmptyValidator extends BaseValidator {
    @Override
    public boolean validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return validateNext(input);
    }
}
