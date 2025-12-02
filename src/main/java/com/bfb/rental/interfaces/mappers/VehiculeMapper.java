package com.bfb.rental.interfaces.mappers;


import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.dtos.CreateVehiculeDto;
import com.bfb.rental.business.vehicles.model.Vehicule;
import lombok.experimental.UtilityClass;


import java.time.LocalDateTime;
import java.util.UUID;
@UtilityClass
public class VehiculeMapper {

    public static Vehicule toEntity(final CreateVehiculeDto dto) {
        return Vehicule.builder()
                .id(UUID.randomUUID())
                .marque(dto.getMarque())
                .modele(dto.getModele())
                .motorisation(dto.getMotorisation())
                .couleur(dto.getCouleur())
                .immatriculation(dto.getImmatriculation())
                .dateAcquisition(dto.getDateAcquisition())
                .prixLocationJournalier(dto.getPrixLocationJournalier())
                .etat(EtatVehicule.AVAILABLE)
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
    }
}