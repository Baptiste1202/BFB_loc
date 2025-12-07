package com.bfb.rental.interfaces.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import com.bfb.rental.business.vehicles.VehicleService;
import com.bfb.rental.interfaces.dtos.vehicles.CreateVehiculeDto;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.interfaces.dtos.vehicles.UpdateVehiculeDto;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import com.bfb.rental.interfaces.mappers.VehiculeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/imt/v1/vehicules")
@Tag(name = "Véhicules", description = "Gestion des véhicules")
@Slf4j
public class VehiculeController {

    private final VehicleService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère tous les véhicules")
    public Collection<TransportVehicle> getAll() {
        return Objects.requireNonNullElse(this.service.getAll(), Collections.emptySet());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crée un nouveau véhicule")
    public TransportVehicle create(@RequestBody final CreateVehiculeDto input) {
        log.info("Création d'un nouveau véhicule : {} {}", input.getMarque(), input.getModele());
        return this.service.create(VehiculeMapper.toEntity(input));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idVehicule}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère un véhicule par ID")
    public TransportVehicle getOne(@PathVariable("idVehicule") final String identifier) {
        return this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le véhicule d'identifiant %s n'a pas été trouvé.", identifier)
                ));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idVehicule}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifie un véhicule")
    public void update(
            @PathVariable("idVehicule") final String identifier,
            @RequestBody final UpdateVehiculeDto input
    ) {
        log.info("Modification du véhicule : {}", identifier);

        TransportVehicle existing = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le véhicule d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        TransportVehicle updated = VehiculeMapper.updateEntity(input, existing);
        this.service.update(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idVehicule}")
    @Operation(summary = "Supprime un véhicule")
    public void delete(@PathVariable("idVehicule") final String identifier) {
        log.info("Suppression du véhicule : {}", identifier);
        this.service.delete(UUID.fromString(identifier));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idVehicule}/panne", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Déclare un véhicule en panne et annule les contrats PENDING")
    public TransportVehicle declarePanne(@PathVariable("idVehicule") final String identifier) {
        log.info("Déclaration en panne du véhicule : {}", identifier);
        return this.service.declarePanne(UUID.fromString(identifier));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idVehicule}/reparer", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Répare un véhicule")
    public TransportVehicle repair(@PathVariable("idVehicule") final String identifier) {
        log.info("Réparation du véhicule : {}", identifier);
        return this.service.repair(UUID.fromString(identifier));
    }
}