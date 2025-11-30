package com.bfb.rental.validateur;

public abstract class BaseValidator<T> implements Validator<T> {

    protected Validator<T> next;

    @Override
    public void setNext(Validator<T> next) {
        this.next = next;
    }

    protected void validateNext(T object) {
        if (next != null) {
            next.validate(object);
        }
    }
}
