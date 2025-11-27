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
                .dateDebut(entity.getDateDebut().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate())
                .dateFin(entity.getDateFin().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate())
                .etat(EtatContrat.valueOf(entity.getEtat()))
                .montantTotal(entity.getMontantTotal())
                .dateRetourReel(entity.getDateRetourReel() != null ?
                        entity.getDateRetourReel().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDate() : null)
                .penaliteRetard(entity.getPenaliteRetard())
                .motifAnnulation(entity.getMotifAnnulation())
                .dateCreation(entity.getDateCreation().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime())
                .dateModification(entity.getDateModification().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime())
                .build();
    }

    @Override
    public ContratEntity to(final Contrat model) {
        if (model == null) return null;

        return ContratEntity.builder()
                .identifier(model.getId().toString())
                .clientId(model.getClientId().toString())
                .vehiculeId(model.getVehiculeId().toString())
                .dateDebut(java.util.Date.from(
                        model.getDateDebut()
                                .atStartOfDay(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .dateFin(java.util.Date.from(
                        model.getDateFin()
                                .atStartOfDay(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .etat(model.getEtat().name())
                .montantTotal(model.getMontantTotal())
                .dateRetourReel(model.getDateRetourReel() != null ?
                        java.util.Date.from(
                                model.getDateRetourReel()
                                        .atStartOfDay(java.time.ZoneId.systemDefault())
                                        .toInstant()) : null)
                .penaliteRetard(model.getPenaliteRetard())
                .motifAnnulation(model.getMotifAnnulation())
                .dateCreation(java.util.Date.from(
                        model.getDateCreation()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .dateModification(java.util.Date.from(
                        model.getDateModification()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .build();
    }
}