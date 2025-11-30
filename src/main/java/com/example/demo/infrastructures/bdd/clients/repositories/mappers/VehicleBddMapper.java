package com.example.demo.infrastructures.bdd.clients.repositories.mappers;

import java.util.UUID;

import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.infrastructures.bdd.clients.repositories.entities.VehicleEntity;
import com.example.demo.infrastructures.bdd.common.model.mappers.AbstractBddMapper;

@Mapper
public class VehicleBddMapper extends AbstractBddMapper<Vehicle, VehicleEntity> {

    @Override
    public Vehicle from(final VehicleEntity input) {
        return Vehicle.builder()
                .identifier(UUID.fromString(input.getIdentifier().toString()))
                .brand(input.getBrand())
                .model(input.getModel())
                .build();
    }

    @Override
    public VehicleEntity to(final Vehicle input) {
        VehicleEntity entity = new VehicleEntity();
        entity.setIdentifier(input.getIdentifier());
        entity.setBrand(input.getBrand());
        entity.setModel(input.getModel());
        return entity;
    }
}
