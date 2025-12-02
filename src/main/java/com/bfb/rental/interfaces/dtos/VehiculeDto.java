package com.bfb.rental.interfaces.dtos;

import com.bfb.rental.business.common.EtatVehicule;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import lombok.*;

/**
 * JUSTIFICATION : DTO Véhicule
 *
 * Structure des données retournées par l'API.
 * Contient UNIQUEMENT les infos nécessaires aux clients.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeDto {

    private String id;
    private String marque;
    private String modele;
    private String motorisation;
    private String couleur;
    private String immatriculation;
    private LocalDate dateAcquisition;
    private EtatVehicule etat;
    private BigDecimal prixLocationJournalier;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private Integer nombreLocationsActuelles;

    /**
     * Champ calculé depuis la BDD (nombre de jours depuis acquisition)
     */
    @JsonProperty("ancienneteAnnees")
    public int getAncienneteAnnees() {
        return Period.between(dateAcquisition, LocalDate.now()).getYears();
    }
}
