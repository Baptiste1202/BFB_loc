package com.bfb.rental.infrastructures.bdd.contrats.repositories.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
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

    private Client client;
    private TransportVehicle vehicule;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private String etat;
    private BigDecimal montantTotal;

    private LocalDate dateRetourReel;
    private BigDecimal penaliteRetard;
    private String motifAnnulation;

    private Date dateCreation;
    private Date dateModification;
}