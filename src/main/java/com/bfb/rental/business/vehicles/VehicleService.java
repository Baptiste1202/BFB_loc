package com.bfb.rental.business.vehicles;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.infrastructures.bdd.contrats.ContratBddService;
import com.bfb.rental.infrastructures.bdd.vehicules.VehiculeBddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehiculeBddService bddService;
    private final ContratBddService contratBddService;

    public Collection<TransportVehicle> getAll() {
        return Objects.requireNonNullElse(this.bddService.getAll(), Collections.emptySet());
    }

    public Optional<TransportVehicle> getOne(final UUID identifier) {
        return this.bddService.get(identifier);
    }

    public TransportVehicle create(final TransportVehicle newVehicule) {
        log.info("Création d'un nouveau véhicule : {} {}",
                newVehicule.getMarque(), newVehicule.getModele());
        return this.bddService.save(newVehicule);
    }

    public void update(final TransportVehicle updatedVehicule) {
        log.info("Mise à jour du véhicule : {}", updatedVehicule.getId());
        this.bddService.save(updatedVehicule);
    }

    public void delete(final UUID identifier) {
        log.info("Suppression du véhicule : {}", identifier);
        this.bddService.delete(identifier);
    }

    /**
     * Déclare le véhicule en panne et annule les contrats PENDING
     */
    public TransportVehicle declarePanne(final UUID identifier) {
        log.info("Déclaration en panne du véhicule : {}", identifier);

        TransportVehicle vehicule = this.getOne(identifier)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Le véhicule d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        vehicule.setEtat(EtatVehicule.BROKE);
        this.bddService.save(vehicule);

        this.contratBddService.cancelInProgressContractsByVehicle(identifier);

        return vehicule;
    }

    /**
     * Répare le véhicule
     */
    public TransportVehicle repair(final UUID identifier) {
        log.info("Réparation du véhicule : {}", identifier);

        TransportVehicle vehicule = this.getOne(identifier)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Le véhicule d'identifiant %s n'a pas été trouvé.", identifier)
                ));

        vehicule.setEtat(EtatVehicule.AVAILABLE);
        this.bddService.save(vehicule);

        return vehicule;
    }
}