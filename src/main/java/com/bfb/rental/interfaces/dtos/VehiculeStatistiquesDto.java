package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.*;
/**
 * JUSTIFICATION : Stats aggrégées du véhicule
 *
 * Utiles pour le dashboard :
 * - Taux d'utilisation
 * - Revenus totaux
 * - Fiabilité (contrats terminés vs annulés)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeStatistiquesDto {

    /**
     * Nombre total de locations
     */
    private Integer totalLocations;

    /**
     * Nombre de locations complètement terminées
     */
    private Integer locationsTerminees;

    /**
     * Nombre de locations en retard
     */
    private Integer locationsEnRetard;

    /**
     * Nombre de locations annulées
     */
    private Integer locationsAnnulees;

    /**
     * Revenus totaux générés
     */
    private BigDecimal revenusTotal;

    /**
     * Montant moyen par location
     */
    @JsonProperty("revenuMoyenParLocation")
    public BigDecimal getRevenuMoyenParLocation() {
        if (totalLocations == null || totalLocations == 0) {
            return BigDecimal.ZERO;
        }
        return revenusTotal.divide(
                BigDecimal.valueOf(totalLocations),
                2,
                RoundingMode.HALF_UP
        );
    }

    /**
     * Taux de fiabilité (% de locations sans problème)
     */
    @JsonProperty("tauxFiabilite")
    public Double getTauxFiabilite() {
        if (totalLocations == null || totalLocations == 0) {
            return 100.0;
        }
        int problemes = (locationsEnRetard != null ? locationsEnRetard : 0) +
                (locationsAnnulees != null ? locationsAnnulees : 0);
        return ((totalLocations - problemes) * 100.0) / totalLocations;
    }

    /**
     * Taux d'utilisation (jours loué / jours depuis acquisition)
     */
    @JsonProperty("tauxUtilisation")
    private Double tauxUtilisation; // Calculé par le service
}
