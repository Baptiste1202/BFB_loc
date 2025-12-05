package com.bfb.rental.business.vehicles.factories;

import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import com.bfb.rental.business.common.EtatVehicule;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class VehicleFactory {

    private VehicleFactory() {
        // Classe utilitaire non instantiable
    }

    public static TransportVehicle createVehicle(
            UUID id,
            String type,
            String marque,
            String modele,
            String motorisation,
            String couleur,
            String immatriculation,
            LocalDate dateAcquisition,
            EtatVehicule etat,
            BigDecimal prixLocationJournalier,
            LocalDateTime dateCreation,
            LocalDateTime dateModification,
            Object specificData) {

        switch (type.toUpperCase()) {
            case "VOITURE":
                int nombrePlaces = extractNombrePlaces(specificData);
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
                        .nombrePlaces(nombrePlaces)
                        .build();

            case "CAMION":
                double volume = extractVolume(specificData);
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
                        .volume(volume)
                        .build();

            default:
                throw new IllegalArgumentException("Type de véhicule inconnu : " + type);
        }
    }

    private static int extractNombrePlaces(Object specificData) {
        if (specificData instanceof Integer) {
            return (Integer) specificData;
        }
        return 5; // Valeur par défaut
    }

    private static double extractVolume(Object specificData) {
        if (specificData instanceof Double) {
            return (Double) specificData;
        }
        return 0.0; // Valeur par défaut
    }
}
