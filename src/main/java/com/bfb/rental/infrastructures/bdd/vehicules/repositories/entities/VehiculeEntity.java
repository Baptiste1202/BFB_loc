package com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehicules")
public class VehiculeEntity {

    @Id
    private String identifier;

    private String marque;
    private String modele;
    private String motorisation;
    private String couleur;
    private String immatriculation;
    private Date dateAcquisition;
    private String etat;
    private BigDecimal prixLocationJournalier;
    private Date dateCreation;
    private Date dateModification;
}