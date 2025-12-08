package com.bfb.rental.business.vehicles.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Setter;

public interface TransportVehicle {

    UUID getId();
    String getType();
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

    void setId(UUID uuid);
    void setMarque(String marque);
    void setModele(String modele);
    void setMotorisation(String motorisation);
    void setCouleur(String couleur);
    void setDateAcquisition(LocalDate dateAcquisition);
    void setPrixLocationJournalier(BigDecimal prixLocationJournalier);
    void setEtat(EtatVehicule etatVehicule);
    void setDateCreation(LocalDateTime now);
    void setDateModification(LocalDateTime dateModification);
    void setImmatriculation(@NotBlank(message = "L'immatriculation est obligatoire") @Pattern(regexp = "^[A-Z0-9]{4,10}$", message = "Format d'immatriculation invalide") String immatriculation);
}
