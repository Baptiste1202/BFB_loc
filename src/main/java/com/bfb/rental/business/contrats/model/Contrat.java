package com.bfb.rental.business.contrats.model;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
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

    // Constructeurs
    public Contrat() {
    }

    public Contrat(UUID id, UUID clientId, UUID vehiculeId, LocalDate dateDebut, LocalDate dateFin,
                   EtatContrat etat, BigDecimal montantTotal, LocalDate dateRetourReel,
                   BigDecimal penaliteRetard, String motifAnnulation, LocalDateTime dateCreation,
                   LocalDateTime dateModification) {
        this.id = id;
        this.clientId = clientId;
        this.vehiculeId = vehiculeId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.montantTotal = montantTotal;
        this.dateRetourReel = dateRetourReel;
        this.penaliteRetard = penaliteRetard;
        this.motifAnnulation = motifAnnulation;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }}