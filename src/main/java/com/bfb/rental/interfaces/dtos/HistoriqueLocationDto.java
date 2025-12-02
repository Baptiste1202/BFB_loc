package com.bfb.rental.interfaces.dtos;

import com.bfb.rental.business.common.EtatContrat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.*;

/**
 * JUSTIFICATION : DTO pour un élément de l'historique
 *
 * Version "light" du contrat, juste pour afficher dans un historique
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriqueLocationDto {

    private String contratId;

    /**
     * Infos du client qui a loué
     */
    @JsonProperty("client")
    private String clientNomComplet; // "Jean Dupont"

    /**
     * Période de location
     */
    private LocalDate dateDebut;
    private LocalDate dateFin;

    /**
     * État du contrat
     */
    private EtatContrat etat;

    /**
     * Montant payé
     */
    private BigDecimal montantTotal;

    /**
     * Timestamp : quand s'est-il passé
     */
    private LocalDateTime dateCreation;
}