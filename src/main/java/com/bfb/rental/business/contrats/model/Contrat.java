package com.bfb.rental.business.contrats.model;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class Contrat {
    private UUID id;
    private Client client;
    private TransportVehicle vehicule;
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

    public Contrat(UUID id, Client client, TransportVehicle vehicule, LocalDate dateDebut, LocalDate dateFin,
                   EtatContrat etat, BigDecimal montantTotal, LocalDate dateRetourReel,
                   BigDecimal penaliteRetard, String motifAnnulation, LocalDateTime dateCreation,
                   LocalDateTime dateModification) {
        this.id = id;
        this.client = client;
        this.vehicule = vehicule;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etat = etat;
        this.montantTotal = montantTotal;
        this.dateRetourReel = dateRetourReel;
        this.penaliteRetard = penaliteRetard;
        this.motifAnnulation = motifAnnulation;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public TransportVehicle getVehicule() {
        return vehicule;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public EtatContrat getEtat() {
        return etat;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public LocalDate getDateRetourReel() {
        return dateRetourReel;
    }

    public BigDecimal getPenaliteRetard() {
        return penaliteRetard;
    }

    public String getMotifAnnulation() {
        return motifAnnulation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVehicule(TransportVehicle vehicule) {
        this.vehicule = vehicule;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void setEtat(EtatContrat etat) {
        this.etat = etat;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setDateRetourReel(LocalDate dateRetourReel) {
        this.dateRetourReel = dateRetourReel;
    }

    public void setPenaliteRetard(BigDecimal penaliteRetard) {
        this.penaliteRetard = penaliteRetard;
    }

    public void setMotifAnnulation(String motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }
}