package com.bfb.rental.business.vehicles.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;

import com.bfb.rental.business.common.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voiture implements TransportVehicle {
    private UUID id;
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
    private int nombrePlaces;

    @Override
    public String getType() {
        return VehicleType.VOITURE.name();
    }

    @Override
    public LocalDateTime getDateCreation() {
        return this.dateCreation;
    }

    @Override
    public LocalDateTime getDateModification() {
        return this.dateModification;
    }

    @Override
    public BigDecimal getPrixLocationJournalier() {
        return this.prixLocationJournalier;
    }

    @Override
    public EtatVehicule getEtat() {
        return this.etat;
    }

    @Override
    public LocalDate getDateAcquisition() {
        return this.dateAcquisition;
    }

    @Override
    public String getImmatriculation() {
        return this.immatriculation;
    }

    @Override
    public String getCouleur() {
        return this.couleur;
    }

    @Override
    public String getMotorisation() {
        return this.motorisation;
    }

    @Override
    public String getModele() {
        return this.modele;
    }

    @Override
    public String getMarque() {
        return this.marque;
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
