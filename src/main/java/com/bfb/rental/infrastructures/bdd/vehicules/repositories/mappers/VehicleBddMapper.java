package com.bfb.rental.infrastructures.bdd.vehicules.repositories.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Vehicule;
import com.bfb.rental.business.vehicles.model.Voiture;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities.VehiculeEntity;

@Component
public class VehiculeBddMapper extends AbstractBddMapper<Vehicule, VehiculeEntity> {

    @Override
    public Vehicule from(final VehiculeEntity entity) {
        if (entity == null) return null;

        return TransportVehicle.builder()
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

    /**
     * Convertit un VehiculeEntity en TransportVehicle (Voiture ou Camion)
     */
    public TransportVehicle fromTransportVehicle(final VehiculeEntity entity) {
        if (entity == null) return null;

        UUID id = UUID.fromString(entity.getIdentifier());
        String marque = entity.getMarque();
        String modele = entity.getModele();
        String motorisation = entity.getMotorisation();
        String couleur = entity.getCouleur();
        String immatriculation = entity.getImmatriculation();
        var dateAcquisition = entity.getDateAcquisition().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
        EtatVehicule etat = EtatVehicule.valueOf(entity.getEtat());
        var prixLocationJournalier = entity.getPrixLocationJournalier();
        var dateCreation = entity.getDateCreation().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        var dateModification = entity.getDateModification().toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();

        if ("VOITURE".equals(entity.getType())) {
            return Voiture.builder()
                    .id(id)
                    .marque(marque)
                    .modele(modele)
                    .motorisation(motorisation)
                    .couleur(couleur)
                    .immatriculation(immatriculation)
                    .dateAcquisition(dateAcquisition)
                    .etat(etat)
                    .prixLocationJournalier(prixLocationJournalier)
                    .dateCreation(dateCreation)
                    .dateModification(dateModification)
                    .nombrePlaces(entity.getNombrePlaces() != null ? entity.getNombrePlaces() : 5)
                    .build();
        } else if ("CAMION".equals(entity.getType())) {
            return Camion.builder()
                    .id(id)
                    .marque(marque)
                    .modele(modele)
                    .motorisation(motorisation)
                    .couleur(couleur)
                    .immatriculation(immatriculation)
                    .dateAcquisition(dateAcquisition)
                    .etat(etat)
                    .prixLocationJournalier(prixLocationJournalier)
                    .dateCreation(dateCreation)
                    .dateModification(dateModification)
                    .volume(entity.getVolume() != null ? entity.getVolume() : 0.0)
                    .build();
        }

        // Fallback sur Vehicule
        return from(entity);
    }

    /**
     * Convertit un TransportVehicle (Voiture ou Camion) en VehiculeEntity
     */
    public VehiculeEntity toTransportVehicle(final TransportVehicle model) {
        if (model == null) return null;

        VehiculeEntity.VehiculeEntityBuilder builder = VehiculeEntity.builder()
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
                .type(model.getType());

        if (model instanceof Voiture) {
            Voiture voiture = (Voiture) model;
            builder.nombrePlaces(voiture.getNombrePlaces());
        } else if (model instanceof Camion) {
            Camion camion = (Camion) model;
            builder.volume(camion.getVolume());
        }

        return builder.build();
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
