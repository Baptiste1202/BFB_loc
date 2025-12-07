package com.bfb.rental.business.common;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public enum EtatContrat {
    IN_PROGRESS,
    ACTIVE,
    ENDED,
    LATE,
    CANCELED;

    public static final String ACCEPTABLE_VALUES = Set.of(
            IN_PROGRESS,
            ACTIVE,
            ENDED,
            LATE,
            CANCELED).stream().map(Enum::name).collect(Collectors.joining(", "));

    public static EtatContrat fromOrDefault(final String name) {
        return EtatContrat.from(name).orElse(EtatContrat.ENDED);
    }

    public static Optional<EtatContrat> from(final String name) {
        try {
            return Optional.ofNullable(name)
                    .map(EtatContrat::valueOf);
        } catch (final IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
