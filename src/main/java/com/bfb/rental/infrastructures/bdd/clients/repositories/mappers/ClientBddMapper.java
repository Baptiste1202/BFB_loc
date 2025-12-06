package com.bfb.rental.infrastructures.bdd.clients.repositories.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.infrastructures.bdd.clients.repositories.entities.ClientEntity;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;

@Component
public class ClientBddMapper extends AbstractBddMapper<Client, ClientEntity> {

    @Override
    public Client from(final ClientEntity entity) {
        if (entity == null) return null;

        return Client.builder()
                .id(UUID.fromString(entity.getIdentifier()))
                .nom(entity.getLastname())
                .prenom(entity.getFirstname())
                .dateNaissance(entity.getDate_of_birth())
                .numPermis(entity.getNum_permis())
                .adresse(entity.getAddress())
                .dateCreation(entity.getDateCreation() != null ?
                        entity.getDateCreation().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime() : null)
                .dateModification(entity.getDateModification() != null ?
                        entity.getDateModification().toInstant()
                                .atZone(java.time.ZoneId.systemDefault())
                                .toLocalDateTime() : null)
                .build();
    }

    @Override
    public ClientEntity to(final Client model) {
        if (model == null) return null;

        return ClientEntity.builder()
                .identifier(model.getId().toString())
                .lastname(model.getNom())
                .firstname(model.getPrenom())
                .date_of_birth(model.getDateNaissance())
                .num_permis(model.getNumPermis())
                .address(model.getAdresse())
                .dateCreation(model.getDateCreation() != null ?
                        java.util.Date.from(
                                model.getDateCreation().atZone(java.time.ZoneId.systemDefault()).toInstant())
                        : null)
                .dateModification(model.getDateModification() != null ?
                        java.util.Date.from(
                                model.getDateModification().atZone(java.time.ZoneId.systemDefault()).toInstant())
                        : null)
                .build();
    }
}