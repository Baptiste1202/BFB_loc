package com.bfb.rental.interfaces.dtos;

import com.bfb.rental.business.common.EtatVehicule;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


/**
 * JUSTIFICATION : DTO de référence pour Véhicule
 *
 * Version light du VehiculeDto pour l'inclure dans d'autres DTOs.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeReferenceDto {

    private String id;
    private String marque;
    private String modele;
    private String immatriculation;
    private EtatVehicule etat;

    @JsonProperty("modeleComplet")
    public String getModeleComplet() {
        return marque + " " + modele;
    }
}