package com.bfb.rental.infrastructures.bdd.vehicules.repositories.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.model.Vehicule;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities.VehiculeEntity;

@Component
public class VehiculeBddMapper extends AbstractBddMapper<Vehicule, VehiculeEntity> {

    @Override
    public Vehicule from(final VehiculeEntity entity) {
        if (entity == null) return null;

        return Vehicule.builder()
                .id(UUID.fromString(entity.getIdentifier()))
                .marque(entity.getMarque())
                .modele(entity.getModele())
                .motorisation(entity.getMotorisation())
                .couleur(entity.getCouleur())
                .immatriculation(entity.getImmatriculation())
                .dateAcquisition(entity.getDateAcquisition().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate())
                .etat(EtatVehicule.valueOf(entity.getEtat()))
                .prixLocationJournalier(entity.getPrixLocationJournalier())
                .dateCreation(entity.getDateCreation().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime())
                .dateModification(entity.getDateModification().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime())
                .build();
    }

    @Override
    public VehiculeEntity to(final Vehicule model) {
        if (model == null) return null;

        return VehiculeEntity.builder()
                .identifier(model.getId().toString())
                .marque(model.getMarque())
                .modele(model.getModele())
                .motorisation(model.getMotorisation())
                .couleur(model.getCouleur())
                .immatriculation(model.getImmatriculation())
                .dateAcquisition(java.util.Date.from(
                        model.getDateAcquisition()
                                .atStartOfDay(java.time.ZoneId.systemDefault())
                                .toInstant()))
                .etat(model.getEtat().name())
                .prixLocationJournalier(model.getPrixLocationJournalier())
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