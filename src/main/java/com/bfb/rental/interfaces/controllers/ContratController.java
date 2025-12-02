package com.bfb.rental.interfaces.controllers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.business.contrats.ContratsService;
import com.bfb.rental.business.contrats.dtos.CreateContratDto;
import com.bfb.rental.business.contrats.dtos.UpdateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import com.bfb.rental.interfaces.mappers.ContratMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
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
        return this.service.create(ContratMapper.toEntity(input));
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idContrat}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Modifie un contrat")
    public void update(
            @PathVariable("idContrat") final String identifier,
            @RequestBody final UpdateContratDto input
    ) {
        log.info("Modification du contrat : {}", identifier);

        Contrat existing = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        Contrat updated = UpdateContratDto.merge(input, existing);
        this.service.update(updated);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idContrat}")
    @Operation(summary = "Supprime un contrat")
    public void delete(@PathVariable("idContrat") final String identifier) {
        log.info("Suppression du contrat : {}", identifier);
        this.service.delete(UUID.fromString(identifier));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idContrat}/initier", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Initie un contrat")
    public Contrat initiate(@PathVariable("idContrat") final String identifier) {
        log.info("Initiation du contrat : {}", identifier);

        Contrat contrat = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        contrat.setEtat(EtatContrat.IN_PROGRESS);
        this.service.update(contrat);

        return contrat;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idContrat}/terminer", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Termine un contrat")
    public Contrat terminate(
            @PathVariable("idContrat") final String identifier,
            @RequestParam(required = false) final LocalDate dateRetour
    ) {
        log.info("Terminaison du contrat : {} - date retour: {}", identifier, dateRetour);

        Contrat contrat = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        LocalDate returnDate = dateRetour != null ? dateRetour : LocalDate.now();
        boolean isLate = returnDate.isAfter(contrat.getDateFin());
        EtatContrat newState = isLate ? EtatContrat.LATE : EtatContrat.ENDED;

        contrat.setEtat(newState);
        contrat.setDateRetourReel(returnDate);
        this.service.update(contrat);

        return contrat;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{idContrat}/annuler", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Annule un contrat")
    public Contrat cancel(
            @PathVariable("idContrat") final String identifier,
            @RequestParam(required = false) final String motif
    ) {
        log.info("Annulation du contrat : {} - motif: {}", identifier, motif);

        Contrat contrat = this.service.getOne(UUID.fromString(identifier))
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Le contrat d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        contrat.setEtat(EtatContrat.CANCELED);
        contrat.setMotifAnnulation(motif);
        this.service.update(contrat);

        return contrat;
    }
}