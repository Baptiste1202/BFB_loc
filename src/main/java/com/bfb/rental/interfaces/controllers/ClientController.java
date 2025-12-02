package com.bfb.rental.interfaces.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import com.bfb.rental.business.clients.ClientsService;
import com.bfb.rental.business.clients.dtos.CreateClientDto;
import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.interfaces.dtos.UpdateClientDto;
import com.bfb.rental.interfaces.mappers.ClientMapper;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/imt/v1/clients")
@Tag(name = "Clients", description = "Gestion des clients")
@Slf4j
public class ClientController {

    private final ClientsService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère tous les clients")
    public Collection<Client> getAll() {
        return Objects.requireNonNullElse(this.service.getAll(), Collections.emptySet());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère un client par ID")
    public Client getOne(@PathVariable String idClient) {
        return this.service.getOne(UUID.fromString(idClient))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le client d'identifiant %s n'a pas été trouvé.", idClient)
                ));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crée un nouveau client")
    public Client create(@RequestBody final CreateClientDto input) {
        log.info("Création d'un nouveau client");
        return this.service.create(ClientMapper.toEntity(input));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifie un client")
    public void update(
            @PathVariable String idClient,
            @RequestBody final UpdateClientDto input
    ) {
        log.info("Modification du client : {}", idClient);

        Client existing = this.service.getOne(UUID.fromString(idClient))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le client d'identifiant %s n'a pas été trouvé.", idClient)
                ));

        Client updated = UpdateClientDto.merge(input, existing);
        this.service.update(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idClient}")
    @Operation(summary = "Supprime un client")
    public void delete(@PathVariable String idClient) {
        log.info("Suppression du client : {}", idClient);
        this.service.delete(UUID.fromString(idClient));
    }
}