package com.bfb.rental.interfaces.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import com.bfb.rental.business.contrats.ContratsService;
import com.bfb.rental.interfaces.dtos.contrats.CreateContratDto;
import com.bfb.rental.interfaces.dtos.contrats.UpdateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.pricing.PricingService;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import com.bfb.rental.interfaces.mappers.ContratMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.bfb.rental.validateur.ContratValidationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imt/v1/contrats")
@Tag(name = "Contrats", description = "Gestion des contrats de location")
@Slf4j
public class ContratController {

    private final ContratsService service;
    private final ContratValidationService validationService;
    private final PricingService pricingService;

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
        Contrat contrat = ContratMapper.toEntity(input);
        this.validationService.validate(contrat);
        return this.service.create(contrat);
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
        // this.validationService.validate(input);
        return this.service.update(UUID.fromString(identifier), input);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idContrat}")
    @Operation(summary = "Supprime un contrat")
    public void delete(@PathVariable("idContrat") final String identifier) {
        log.info("Suppression du contrat : {}", identifier);
        this.service.delete(UUID.fromString(identifier));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idContrat}/prix", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Calcule le prix d'un contrat selon une stratégie")
    public Map<String, Object> calculatePrice(
            @PathVariable("idContrat") final String identifier,
            @RequestParam(defaultValue = "BASIQUE") final String strategie
    ) {
        log.info("Calcul du prix du contrat : {} avec la stratégie : {}", identifier, strategie);

        Contrat contrat = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        BigDecimal prix = this.pricingService.calculateContractPrice(contrat, strategie);

        return Map.of(
                "contratId", identifier,
                "strategie", strategie,
                "prixTotal", prix
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{idContrat}/prix/comparaison", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Compare les prix selon toutes les stratégies disponibles")
    public Map<String, Object> compareAllPrices(@PathVariable("idContrat") final String identifier) {
        log.info("Comparaison des prix pour le contrat : {}", identifier);

        Contrat contrat = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        List<String> strategies = List.of("BASIQUE", "RÉDUCTION_HEBDOMADAIRE",
                "SAISONNIÈRE", "RÉDUCTION_PROGRESSIVE");

        Map<String, BigDecimal> comparaison = new HashMap<>();
        for (String strategie : strategies) {
            BigDecimal prix = this.pricingService.calculateContractPrice(contrat, strategie);
            comparaison.put(strategie, prix);
        }

        return Map.of(
                "contratId", identifier,
                "comparaison", comparaison
        );
    }
}