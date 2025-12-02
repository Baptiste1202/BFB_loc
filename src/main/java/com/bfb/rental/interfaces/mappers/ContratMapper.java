package com.bfb.rental.interfaces.mappers;

import lombok.experimental.UtilityClass;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.contrats.dtos.CreateContratDto;
import java.time.LocalDateTime;
import com.bfb.rental.business.common.EtatContrat;
import java.util.UUID;

@UtilityClass
public class ContratMapper {

    public static Contrat toEntity(final CreateContratDto dto) {
        return Contrat.builder()
                .id(UUID.randomUUID())
                .clientId(dto.getClientId())
                .vehiculeId(dto.getVehiculeId())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .etat(EtatContrat.PENDING)
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
    }
}