package com.bfb.rental.business.vehicles;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.infrastructures.bdd.vehicules.VehiculeBddService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VehicleService.class);

    private final VehiculeBddService bddService;

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
}

