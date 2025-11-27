package com.bfb.rental.business.vehicles;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.vehicles.model.Vehicule;
import com.bfb.rental.infrastructures.bdd.vehicules.VehiculeBddService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehiculeBddService bddService;

    public Collection<Vehicule> getAll() {
        return Objects.requireNonNullElse(this.bddService.getAll(), Collections.emptySet());
    }

    public Optional<Vehicule> getOne(final UUID identifier) {
        return this.bddService.get(identifier);
    }

    public Vehicule create(final Vehicule newVehicule) {
        log.info("Création d'un nouveau véhicule : {} {}",
                newVehicule.getMarque(), newVehicule.getModele());
        return this.bddService.save(newVehicule);
    }

    public void update(final Vehicule updatedVehicule) {
        log.info("Mise à jour du véhicule : {}", updatedVehicule.getId());
        this.bddService.save(updatedVehicule);
    }

    public void delete(final UUID identifier) {
        log.info("Suppression du véhicule : {}", identifier);
        this.bddService.delete(identifier);
    }
}
