package com.bfb.rental.interfaces.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import com.bfb.rental.business.contrats.ContratsService;
import com.bfb.rental.interfaces.dtos.contrats.CreateContratDto;
import com.bfb.rental.interfaces.dtos.contrats.UpdateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imt/v1/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
@Slf4j
public class ContratController {

    private final ContratsService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère tous les contrats")
    public Collection<Contrat> getAll() {
        return Objects.requireNonNullElse(this.service.getAll(), Collections.emptySet());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crée un nouveau contrat")
    public Contrat create(@RequestBody final CreateContratDto input) {
        log.info("Création d'un nouveau contrat");
        return this.service.create(input);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idContrat}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupère un contrat par ID")
    public Contrat getOne(@PathVariable("idContrat") final String identifier) {
        return this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idContrat}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifie un contrat (change état, dates, pénalité, motif)")
    public Contrat update(
            @PathVariable("idContrat") final String identifier,
            @RequestBody final UpdateContratDto input
    ) {
        log.info("Modification du contrat : {}", identifier);
        return this.service.update(UUID.fromString(identifier), input);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idContrat}")
    @Operation(summary = "Supprime un contrat")
    public void delete(@PathVariable("idContrat") final String identifier) {
        log.info("Suppression du contrat : {}", identifier);
        this.service.delete(UUID.fromString(identifier));
    }
}