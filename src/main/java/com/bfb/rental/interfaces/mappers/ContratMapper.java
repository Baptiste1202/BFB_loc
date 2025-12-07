package com.bfb.rental.interfaces.mappers;

import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.interfaces.dtos.contrats.CreateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class ContratMapper {

    public static Contrat toEntity(final CreateContratDto dto) {
        if (dto == null) return null;

        return Contrat.builder()
                .id(UUID.randomUUID())
                .clientId(dto.getClientId())
                .vehiculeId(dto.getVehiculeId())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .etat(EtatContrat.IN_PROGRESS)
                .dateCreation(LocalDateTime.now())
                .dateModification(null)
                .build();
    }
}