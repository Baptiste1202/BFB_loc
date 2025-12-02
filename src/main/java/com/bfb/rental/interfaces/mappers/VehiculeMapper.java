package com.bfb.rental.interfaces.mappers;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.dtos.CreateCamionDto;
import com.bfb.rental.business.vehicles.dtos.CreateVoitureDto;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class VehiculeMapper {

    public TransportVehicle toEntity(final Object dto) {
        if (dto instanceof CreateCamionDto camionDto) {
            return toCamion(camionDto);
        } else if (dto instanceof CreateVoitureDto voitureDto) {
            return toVoiture(voitureDto);
        }
        throw new IllegalArgumentException("DTO de véhicule inconnu : " + (dto == null ? "null" : dto.getClass().getName()));
    }

    public Camion toCamion(final CreateCamionDto dto) {
        if (dto == null) return null;
        return Camion.builder()
                .id(UUID.randomUUID())
                .marque(dto.getMarque())
                .modele(dto.getModele())
                .motorisation(dto.getMotorisation())
                .couleur(dto.getCouleur())
                .immatriculation(dto.getImmatriculation())
                .dateAcquisition(dto.getDateAcquisition())
                .prixLocationJournalier(dto.getPrixLocationJournalier())
                .volume(dto.getVolume())
                .etat(EtatVehicule.AVAILABLE)
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
    }

    public Voiture toVoiture(final CreateVoitureDto dto) {
        if (dto == null) return null;
        return Voiture.builder()
                .id(UUID.randomUUID())
                .marque(dto.getMarque())
                .modele(dto.getModele())
                .motorisation(dto.getMotorisation())
                .couleur(dto.getCouleur())
                .immatriculation(dto.getImmatriculation())
                .dateAcquisition(dto.getDateAcquisition())
                .prixLocationJournalier(dto.getPrixLocationJournalier())
                .nombrePlaces(dto.getNombrePlaces())
                .etat(EtatVehicule.AVAILABLE)
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
    }

}
