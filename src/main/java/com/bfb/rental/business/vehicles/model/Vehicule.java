package com.bfb.rental.business.vehicles.model;


import com.bfb.rental.business.common.EtatVehicule;
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
public class Vehicule {
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
}