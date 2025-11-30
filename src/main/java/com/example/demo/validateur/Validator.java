package com.example.demo.validateur;

public interface Validator {
    boolean validate(String input);
    void setNext(Validator next);
}