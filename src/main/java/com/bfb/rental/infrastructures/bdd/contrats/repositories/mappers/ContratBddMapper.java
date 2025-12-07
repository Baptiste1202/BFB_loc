package com.bfb.rental.infrastructures.bdd.contrats.repositories.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;
import com.bfb.rental.infrastructures.bdd.contrats.repositories.entities.ContratEntity;

@Component
public class ContratBddMapper extends AbstractBddMapper<Contrat, ContratEntity> {

    @Override
    public Contrat from(final ContratEntity entity) {
        if (entity == null) return null;

        return Contrat.builder()
                .id(UUID.fromString(entity.getIdentifier()))
                .clientId(UUID.fromString(entity.getClientId()))
                .vehiculeId(UUID.fromString(entity.getVehiculeId()))
                .dateDebut(entity.getDateDebut())
                .dateFin(entity.getDateFin())
                .etat(EtatContrat.valueOf(entity.getEtat()))
                .montantTotal(entity.getMontantTotal())
                .dateRetourReel(entity.getDateRetourReel())
                .penaliteRetard(entity.getPenaliteRetard())
                .motifAnnulation(entity.getMotifAnnulation())
                .dateCreation(entity.getDateCreation() != null ?
                        entity.getDateCreation().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime() : null)
                .dateModification(entity.getDateModification() != null ?
                        entity.getDateModification().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime() : null)
                .build();
    }

    @Override
    public ContratEntity to(final Contrat model) {
        if (model == null) return null;

        return ContratEntity.builder()
                .identifier(model.getId().toString())
                .clientId(model.getClientId().toString())
                .vehiculeId(model.getVehiculeId().toString())
                .dateDebut(model.getDateDebut())
                .dateFin(model.getDateFin())
                .etat(model.getEtat().name())
                .montantTotal(model.getMontantTotal())
                .dateRetourReel(model.getDateRetourReel())
                .penaliteRetard(model.getPenaliteRetard())
                .motifAnnulation(model.getMotifAnnulation())
                .dateCreation(model.getDateCreation() != null ?
                        java.util.Date.from(
                                model.getDateCreation().atZone(java.time.ZoneId.systemDefault()).toInstant())
                        : null)
                .dateModification(model.getDateModification() != null ?
                        java.util.Date.from(
                                model.getDateModification().atZone(java.time.ZoneId.systemDefault()).toInstant())
                        : null)
                .build();
    }
}