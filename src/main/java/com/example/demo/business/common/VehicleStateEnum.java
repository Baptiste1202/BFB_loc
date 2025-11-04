package com.example.demo.business.common;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public enum VehicleStateEnum {
    /**
     * Vehicule disponible
     */
    EVAILABLE,

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
    public static final String ACCEPTABLE_VALUES = Set.of(EVAILABLE, RENTED, BROKE).stream().map(Enum::name).collect(Collectors.joining(", "));

    /**
     * Convertit une chaîne en GenreEnum, retourne INCONNU si la valeur n'est pas reconnue.
     *
     * @param name le nom du genre
     * @return l'énumération correspondante ou INCONNU
     */
    public static VehicleStateEnum fromOrDefault(final String name) {
        return VehicleStateEnum.from(name).orElse(VehicleStateEnum.EVAILABLE);
    }
    /**
     * Convertit une chaîne en VehicleStateEnum de manière sécurisée.
     *
     * @param name le nom de l'état
     * @return un Optional contenant l'énumération si valide, Optional.empty() sinon
     */
    public static Optional<VehicleStateEnum> from(final String name) {
        try {
            return Optional.ofNullable(name)
                    .map(VehicleStateEnum::valueOf);
        } catch (final IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
    

