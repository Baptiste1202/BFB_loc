package com.bfb.rental.interfaces.mappers;
import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.interfaces.dtos.clients.CreateClientDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class ClientMapper {

    public static Client toEntity(final CreateClientDto dto) {
        return Client.builder()
                .id(UUID.randomUUID())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .dateNaissance(dto.getDateNaissance())
                .numPermis(dto.getNumPermis())
                .adresse(dto.getAdresse())
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
    }
}