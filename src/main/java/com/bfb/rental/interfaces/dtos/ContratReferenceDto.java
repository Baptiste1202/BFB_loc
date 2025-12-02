package com.bfb.rental.interfaces.dtos;

import com.bfb.rental.business.common.EtatContrat;
import lombok.*;

import java.time.LocalDate;

/**
 * JUSTIFICATION : DTO de référence (light)
 *
 * Utilisé quand on inclut une ressource liée dans une autre DTO.
 * On ne charge QUE les infos nécessaires pour:
 * - Identifier le contrat (id)
 * - Afficher dans une liste (dates, statut)
 *
 * PAS tous les détails du contrat (sinon c'est une N+1 query).
 *
 * PATTERN : Reference Object Pattern
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratReferenceDto {

    private String id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private EtatContrat etat;
    private String vehiculeId;

    /**
     * Affichage court du véhicule
     */
    private String vehiculeMarqueModele; // "Peugeot 308"
}
