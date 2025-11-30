package com.example.demo.validateur;

public class LengthValidator extends BaseValidator {
    private final int minLength;
    private final int maxLength;

    public LengthValidator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(String input) {
        if (input.length() < minLength || input.length() > maxLength) {
            return false;
        }
        return validateNext(input);
    }
    
}
