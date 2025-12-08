package com.bfb.rental.business.vehicles.factories;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.common.VehicleType;
import com.bfb.rental.business.vehicles.dtos.CreateVehiculeDto;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class VehicleFactory {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VehicleFactory.class);

    /**
     * Crée un véhicule selon le type spécifié
     *
     * @param type le type de véhicule (VOITURE ou CAMION)
     * @return une instance du véhicule correspondant
     * @throws IllegalArgumentException si le type est inconnu
     */
    public TransportVehicle createVehicle(String type) {
        VehicleType vehicleType;

        try {
            vehicleType = VehicleType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Type de véhicule inconnu: {}", type);
            throw new IllegalArgumentException("Type de véhicule invalide: " + type);
        }

        TransportVehicle vehicle = switch (vehicleType) {
            case VOITURE -> {
                log.info("Création d'une nouvelle voiture");
                yield new Voiture();
            }
            case CAMION -> {
                log.info("Création d'un nouveau camion");
                yield new Camion();
            }
        };

        // Initialisation commune
        vehicle.setId(UUID.randomUUID());
        vehicle.setDateCreation(LocalDateTime.now());
        vehicle.setDateModification(LocalDateTime.now());
        vehicle.setEtat(EtatVehicule.AVAILABLE);

        return vehicle;
    }

    /**
     * Crée un véhicule à partir d'un DTO
     *
     * @param dto les données du véhicule
     * @return une instance du véhicule avec les données du DTO
     */
    public TransportVehicle createVehicle(CreateVehiculeDto dto) {
        TransportVehicle vehicle = createVehicle(dto.getType());

        // Mapping des propriétés communes
        vehicle.setMarque(dto.getMarque());
        vehicle.setModele(dto.getModele());
        vehicle.setMotorisation(dto.getMotorisation());
        vehicle.setCouleur(dto.getCouleur());
        vehicle.setImmatriculation(dto.getImmatriculation());
        vehicle.setDateAcquisition(dto.getDateAcquisition());
        vehicle.setPrixLocationJournalier(dto.getPrixLocationJournalier());

        // Mapping des propriétés spécifiques
        if (vehicle instanceof Voiture voiture) {
            voiture.setNombrePlaces(dto.getNombrePlaces());
        } else if (vehicle instanceof Camion camion) {
            camion.setVolume(dto.getVolume());
        }

        log.info("Véhicule créé: {} {} {} ({})",
                vehicle.getType(), vehicle.getMarque(), vehicle.getModele(), vehicle.getId());

        return vehicle;
    }

    /**
     * Liste tous les types de véhicules disponibles
     *
     * @return la liste des types disponibles
     */
    public List<String> getAvailableTypes() {
        return Arrays.stream(VehicleType.values())
                .map(VehicleType::name)
                .toList();
    }
}
