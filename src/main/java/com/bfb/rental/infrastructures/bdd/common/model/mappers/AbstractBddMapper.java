package com.bfb.rental.infrastructures.bdd.common.model.mappers;

public abstract class AbstractBddMapper<T, E> {
    public abstract T from(final E input);
    public abstract E to(final T object);
}

