package com.bfb.rental.business.contrats.model;


import com.bfb.rental.business.common.EtatContrat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrat {
    private UUID id;
    private UUID clientId;
    private UUID vehiculeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private EtatContrat etat;
    private BigDecimal montantTotal;
    private LocalDate dateRetourReel;
    private BigDecimal penaliteRetard;
    private String motifAnnulation;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}