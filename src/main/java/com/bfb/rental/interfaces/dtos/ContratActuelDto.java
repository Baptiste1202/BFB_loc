package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.*;
/**
 * JUSTIFICATION : Info du contrat actuel (EN_COURS)
 *
 * Inclus dans VehiculeDetailDto
 * Permet de savoir rapidement qui occupe le véhicule et jusqu'à quand
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratActuelDto {

    private String contratId;

    /**
     * Client qui utilise actuellement
     */
    @JsonProperty("client")
    private String clientNomComplet;
    private String clientId;

    /**
     * Dates de location
     */
    private LocalDate dateDebut;
    private LocalDate dateFin;

    /**
     * Jours restants avant retour
     */
    @JsonProperty("joursRestants")
    public int getJoursRestants() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), dateFin);
    }

    /**
     * Le client est-il en retard (si dateFin < aujourd'hui)
     */
    @JsonProperty("enRetard")
    public boolean isEnRetard() {
        return dateFin.isBefore(LocalDate.now());
    }
}
