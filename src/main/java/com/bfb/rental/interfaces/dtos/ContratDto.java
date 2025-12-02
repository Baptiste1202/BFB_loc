package com.bfb.rental.interfaces.dtos;
import com.bfb.rental.business.common.EtatContrat;
import lombok.*;

import java.time.*;
import java.math.BigDecimal;

/**
 * JUSTIFICATION : DTO Contrat
 *
 * LE PLUS IMPORTANT : Le contrat relie Client + Véhicule.
 * Dans le DTO, on référence les deux (par ID + infos light).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratDto {

    private String id;
    private String clientId;
    private String vehiculeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private EtatContrat etat;

    /**
     * Champs calculés
     */
    private BigDecimal montantTotal;
    private Integer nombreJours;

    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;

    /**
     * JUSTIFICATION : Ne pas exposer directement Client/Véhicule
     * Utiliser des références pour éviter les DataFetcher complexes
     */
}
