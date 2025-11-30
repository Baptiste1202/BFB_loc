package com.bfb.rental.business.contrats;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.contrats.dtos.CreateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.pricing.BasicPricingStrategy;
import com.bfb.rental.infrastructures.bdd.contrats.ContratBddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContratsService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ContratsService.class);
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

    public Contrat createFromDto(final CreateContratDto dto) {
        Contrat contrat = Contrat.builder()
                .id(UUID.randomUUID())
                .client(dto.getClient())
                .vehicule(dto.getVehicule())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .dateCreation(LocalDateTime.now())
                .dateModification(LocalDateTime.now())
                .build();
        
        return this.create(contrat);
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