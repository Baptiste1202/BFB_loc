package com.bfb.rental.business.vehicles.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;

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
    String getType();
}
