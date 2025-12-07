package com.bfb.rental.business.common;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public enum EtatVehicule {
    /**
     * Vehicule disponible
     */
    AVAILABLE,

    /**
     * Vehicule loué
     */
    RENTED,

    /**
     * Véhicule en panne
     */
    BROKE;

    /**
     * Chaîne de caractères listant les valeurs acceptables (exclut INCONNU)
     */
    public static final String ACCEPTABLE_VALUES = Set.of(AVAILABLE, RENTED, BROKE).stream().map(Enum::name).collect(Collectors.joining(", "));

    /**
     * Convertit une chaîne en VehicleStateEnum de manière sécurisée.
     *
     * @param name le nom de l'état
     * @return un Optional contenant l'énumération si valide, Optional.empty() sinon
     */
    public static Optional<EtatVehicule> from(final String name) {
        try {
            return Optional.ofNullable(name)
                    .map(EtatVehicule::valueOf);
        } catch (final IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @JsonCreator
    public static EtatVehicule fromJson(final String value) {
        return EtatVehicule.from(value)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("État invalide: '%s'. Valeurs acceptées: %s", value, ACCEPTABLE_VALUES)
                ));
    }
}
    

