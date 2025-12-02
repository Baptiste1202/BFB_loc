package com.bfb.rental.business.vehicles.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;
import lombok.Builder;
import lombok.Setter;


public interface TransportVehicle {
    UUID getId();
    String getMarque();
    String getModele();
    String getMotorisation();
    String getCouleur();
    String getImmatriculation();
    LocalDate getDateAcquisition();
    EtatVehicule getEtat();
    BigDecimal getPrixLocationJournalier();
    LocalDateTime getDateCreation();
    LocalDateTime getDateModification();
    UUID getId();
    void setId(UUID id);

    String getMarque();
    void setMarque(String marque);

    String getModele();
    void setModele(String modele);

    String getMotorisation();
    void setMotorisation(String motorisation);

    String getCouleur();
    void setCouleur(String couleur);

    String getImmatriculation();
    void setImmatriculation(String immatriculation);

    LocalDate getDateAcquisition();
    void setDateAcquisition(LocalDate dateAcquisition);

    EtatVehicule getEtat();
    void setEtat(EtatVehicule etat);

    BigDecimal getPrixLocationJournalier();
    void setPrixLocationJournalier(BigDecimal prix);

    LocalDateTime getDateCreation();
    void setDateCreation(LocalDateTime dateCreation);

    LocalDateTime getDateModification();
    void setDateModification(LocalDateTime dateModification);
    String getType();
}
