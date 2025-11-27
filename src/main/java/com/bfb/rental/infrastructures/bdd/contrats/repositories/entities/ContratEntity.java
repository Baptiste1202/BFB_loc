package com.bfb.rental.infrastructures.bdd.contrats.repositories.entities;

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
@Document(collection = "contrats")
public class ContratEntity {

    @Id
    private String identifier;

    private String clientId;
    private String vehiculeId;
    private Date dateDebut;
    private Date dateFin;
    private String etat;
    private BigDecimal montantTotal;
    private Date dateRetourReel;
    private BigDecimal penaliteRetard;
    private String motifAnnulation;
    private Date dateCreation;
    private Date dateModification;
}