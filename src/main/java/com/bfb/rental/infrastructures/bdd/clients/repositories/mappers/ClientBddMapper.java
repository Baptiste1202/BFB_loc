package com.bfb.rental.infrastructures.bdd.clients.repositories.mappers;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.vehicles.model.Vehicle;
import com.bfb.rental.infrastructures.bdd.clients.repositories.entities.ClientEntity;
import com.bfb.rental.infrastructures.bdd.clients.repositories.entities.VehicleEntity;
import com.bfb.rental.infrastructures.bdd.common.model.mappers.AbstractBddMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientBddMapper extends AbstractBddMapper<Client, ClientEntity> {
        
    private VehicleBddMapper vehicleMapper;

    @Override
    public Client from(final ClientEntity input) {
        return Client.builder()
                .identifier(UUID.fromString(input.getIdentifier()))
                .lastname(input.getLastname())
                .firstname(input.getFirstname())
                .date_of_birth(input.getDate_of_birth())
                .num_permis(input.getNum_permis())
                .address(input.getAddress())
                .vehicles(Objects.requireNonNullElse(input.getVehicles(), Collections.<VehicleEntity>emptySet()).stream()
                        .map(this.vehicleMapper::from)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public ClientEntity to(final Client object) {
        return ClientEntity.builder()
                .identifier(object.getIdentifier().toString())
                .lastname(object.getLastname())
                .firstname(object.getFirstname())
                .date_of_birth(object.getDate_of_birth())
                .num_permis(object.getNum_permis())
                .address(object.getAddress())
                .vehicles(Objects.requireNonNullElse(object.getVehicles(), Collections.<Vehicle>emptySet()).stream()
                        .map(this.vehicleMapper::to)
                        .collect(Collectors.toSet()))
                .build();
    }
}
