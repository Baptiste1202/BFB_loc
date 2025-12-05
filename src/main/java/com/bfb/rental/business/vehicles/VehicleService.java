package com.bfb.rental.business.vehicles;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.vehicles.dtos.CreateCamionDto;
import com.bfb.rental.business.vehicles.dtos.CreateVoitureDto;
import com.bfb.rental.business.vehicles.factories.VehicleFactory;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import com.bfb.rental.infrastructures.bdd.vehicules.VehiculeBddService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * Crée une voiture en utilisant la Factory Method
     *
     * @param dto les informations de la voiture à créer
     * @return la voiture créée
     */
    public Voiture createVoiture(final CreateVoitureDto dto) {
        log.info("Création d'une voiture : {} {}", dto.getMarque(), dto.getModele());
        
        TransportVehicle transportVehicle = VehicleFactory.createVehicle(
                UUID.randomUUID(),
                "VOITURE",
                dto.getMarque(),
                dto.getModele(),
                dto.getMotorisation(),
                dto.getCouleur(),
                dto.getImmatriculation(),
                dto.getDateAcquisition(),
                EtatVehicule.AVAILABLE,
                dto.getPrixLocationJournalier(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                dto.getNombrePlaces()
        );
        
        Voiture voiture = (Voiture) transportVehicle;
        return (Voiture) this.bddService.save(voiture);
    }

    /**
     * Crée un camion en utilisant la Factory Method
     *
     * @param dto les informations du camion à créer
     * @return le camion créé
     */
    public Camion createCamion(final CreateCamionDto dto) {
        log.info("Création d'un camion : {} {}", dto.getMarque(), dto.getModele());
        
        TransportVehicle transportVehicle = VehicleFactory.createVehicle(
                UUID.randomUUID(),
                "CAMION",
                dto.getMarque(),
                dto.getModele(),
                dto.getMotorisation(),
                dto.getCouleur(),
                dto.getImmatriculation(),
                dto.getDateAcquisition(),
                EtatVehicule.AVAILABLE,
                dto.getPrixLocationJournalier(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                dto.getVolume()
        );
        
        Camion camion = (Camion) transportVehicle;
        return (Camion) this.bddService.save(camion);
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

