package com.bfb.rental.validateur;

public interface Validator<T> {
    void validate(T object);
    void setNext(Validator<T> next);
}