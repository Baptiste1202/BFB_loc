package com.bfb.rental.infrastructures.bdd.vehicules;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Vehicule;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.VehiculeRepository;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities.VehiculeEntity;
import com.bfb.rental.infrastructures.bdd.vehicules.repositories.mappers.VehiculeBddMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehiculeBddService {

    private VehiculeRepository repository;
    private VehiculeBddMapper mapper;

    public boolean exist(final UUID identifier) {
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .map(this.repository::existsById)
                .orElse(false);
    }

    public Collection<Vehicule> getAll() {
        return Objects.requireNonNullElse(this.repository.findAll(), Collections.<VehiculeEntity>emptyList())
                .stream()
                .map(this.mapper::from)
                .collect(Collectors.toSet());
    }

    public Optional<Vehicule> get(final UUID identifier) {
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .flatMap(this.repository::findById)
                .map(this.mapper::from);
    }

    public Vehicule save(final Vehicule vehicule) {
        Objects.requireNonNull(vehicule, "Impossible de sauvegarder un véhicule nul");
        return this.mapper.from(
                this.repository.save(this.mapper.to(vehicule))
        );
    }

    /**
     * Sauvegarde un TransportVehicle (Voiture ou Camion)
     */
    public TransportVehicle save(final TransportVehicle transportVehicle) {
        Objects.requireNonNull(transportVehicle, "Impossible de sauvegarder un véhicule nul");
        VehiculeEntity entity = this.mapper.toTransportVehicle(transportVehicle);
        VehiculeEntity saved = this.repository.save(entity);
        return this.mapper.fromTransportVehicle(saved);
    }

    public void delete(final UUID identifier) {
        Optional.ofNullable(identifier)
                .map(UUID::toString)
                .ifPresent(this.repository::deleteById);
    }
}