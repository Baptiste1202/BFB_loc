package com.bfb.rental.business.contrats;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.infrastructures.bdd.contrats.ContratBddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContratsService {

    private final ContratBddService bddService;

    public Collection<Contrat> getAll() {
        return Objects.requireNonNullElse(this.bddService.getAll(), Collections.emptySet());
    }

    public Optional<Contrat> getOne(final UUID identifier) {
        return this.bddService.get(identifier);
    }

    public Contrat create(final Contrat newContrat) {
        log.info("Création d'un nouveau contrat");
        return this.bddService.save(newContrat);
    }

    public void update(final Contrat updatedContrat) {
        log.info("Mise à jour du contrat : {}", updatedContrat.getId());
        this.bddService.save(updatedContrat);
    }

    public void delete(final UUID identifier) {
        log.info("Suppression du contrat : {}", identifier);
        this.bddService.delete(identifier);
    }
}