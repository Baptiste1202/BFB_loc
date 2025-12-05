package com.bfb.rental.infrastructures.bdd.vehicules.repositories.mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities.VehiculeEntity;

@Component
public class VehiculeBddMapper extends AbstractBddMapper<TransportVehicle, VehiculeEntity> {

        @Override
        public TransportVehicle from(final VehiculeEntity entity) {
                if (entity == null) return null;

                UUID id = entity.getIdentifier() != null ? UUID.fromString(entity.getIdentifier()) : null;
                String marque = entity.getMarque();
                String modele = entity.getModele();
                String motorisation = entity.getMotorisation();
                String couleur = entity.getCouleur();
                String immatriculation = entity.getImmatriculation();
                LocalDate dateAcquisition = entity.getDateAcquisition() != null
                        ? entity.getDateAcquisition().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        : null;
                EtatVehicule etat = entity.getEtat() != null ? EtatVehicule.valueOf(entity.getEtat()) : null;
                BigDecimal prixLocationJournalier = entity.getPrixLocationJournalier();
                LocalDateTime dateCreation = entity.getDateCreation() != null
                        ? entity.getDateCreation().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                        : null;
                LocalDateTime dateModification = entity.getDateModification() != null
                        ? entity.getDateModification().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                        : null;

                if ("VOITURE".equalsIgnoreCase(entity.getType())) {
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
                } else if ("CAMION".equalsIgnoreCase(entity.getType())) {
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

                // Par défaut, retourner une Voiture basique si le type est inconnu
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
                        .nombrePlaces(entity.getNombrePlaces() != null ? entity.getNombrePlaces() : 0)
                        .build();
        }

    @Override
    public VehiculeEntity to(final TransportVehicle model) {
        if (model == null) return null;

        VehiculeEntity.VehiculeEntityBuilder builder = VehiculeEntity.builder()
                .identifier(model.getId() != null ? model.getId().toString() : null)
                .marque(model.getMarque())
                .modele(model.getModele())
                .motorisation(model.getMotorisation())
                .couleur(model.getCouleur())
                .immatriculation(model.getImmatriculation())
                .dateAcquisition(model.getDateAcquisition() != null
                        ? Date.from(model.getDateAcquisition().atStartOfDay(ZoneId.systemDefault()).toInstant())
                        : null)
                .etat(model.getEtat() != null ? model.getEtat().name() : null)
                .prixLocationJournalier(model.getPrixLocationJournalier())
                .dateCreation(model.getDateCreation() != null
                        ? Date.from(model.getDateCreation().atZone(ZoneId.systemDefault()).toInstant())
                        : null)
                .dateModification(model.getDateModification() != null
                        ? Date.from(model.getDateModification().atZone(ZoneId.systemDefault()).toInstant())
                        : null)
                .type(model instanceof Voiture ? "VOITURE" : model instanceof Camion ? "CAMION" : "VOITURE");

        if (model instanceof Voiture) {
            Voiture voiture = (Voiture) model;
            builder.nombrePlaces(voiture.getNombrePlaces());
        } else if (model instanceof Camion) {
            Camion camion = (Camion) model;
            builder.volume(camion.getVolume());
        }

        return builder.build();
    }
}
