package com.bfb.rental.business.contrats;

import com.bfb.rental.business.clients.ClientsService;
import com.bfb.rental.business.common.EtatContrat;
import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.interfaces.dtos.contrats.UpdateContratDto;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.VehicleService;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.infrastructures.bdd.contrats.ContratBddService;
import com.bfb.rental.interfaces.exceptions.ResourceNotFoundException;
import com.bfb.rental.interfaces.mappers.ContratMapper;
import com.bfb.rental.validateur.ContratValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContratsService {


    private final ContratBddService bddService;
    private final ClientsService clientService;
    private final VehicleService vehicleService;
    private final ContratValidationService validationService;

    public Collection<Contrat> getAll() {
        return Objects.requireNonNullElse(this.bddService.getAll(), Collections.emptySet());
    }

    public Optional<Contrat> getOne(final UUID identifier) {
        return this.bddService.get(identifier);
    }

    /**
     * Crée un contrat avec validations
     */
    public Contrat create(final Contrat input) {
        log.info("Création d'un nouveau contrat");

        this.clientService.getOne(input.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Client %s non trouvé", input.getClient())
                ));

        TransportVehicle vehicule = this.vehicleService.getOne(input.getVehicule().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Véhicule %s non trouvé", input.getVehicule())
                ));

        if (vehicule.getEtat() == EtatVehicule.BROKE) {
            throw new IllegalArgumentException(
                    String.format("Le véhicule %s est en panne et ne peut pas être loué",
                            vehicule.getImmatriculation())
            );
        }

        Collection<Contrat> conflicts = this.bddService.findConflictingContracts(
                input.getVehicule().getId(),
                input.getDateDebut(),
                input.getDateFin()
        );

        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Le véhicule %s est déjà loué du %s au %s",
                            vehicule.getImmatriculation(),
                            input.getDateDebut(),
                            input.getDateFin())
            );
        }


        return this.bddService.save(input);
    }

    public Contrat update(final UUID identifier, final UpdateContratDto input) {
        log.info("Modification du contrat : {}", identifier);

        Contrat existing = this.getOne(identifier)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Contrat %s non trouvé", identifier)
                ));

        LocalDate dateFin = input.getDateFin() != null ? input.getDateFin() : existing.getDateFin();

        UpdateContratDto.merge(input, existing);

        if (input.getDateRetourReel() != null && input.getDateRetourReel().isAfter(dateFin)) {
            existing.setEtat(EtatContrat.LATE);
            log.info("Contrat {} en retard", identifier);
        }

        this.validationService.validate(existing);

        return this.bddService.save(existing);
    }

    /**
     * Supprime un contrat
     */
    public void delete(final UUID identifier) {
        log.info("Suppression du contrat : {}", identifier);
        this.bddService.delete(identifier);
    }

    public void cancelPendingContractsByVehicle(final UUID vehiculeId) {
        log.info("Annulation des contrats PENDING pour le véhicule : {}", vehiculeId);

        for (Contrat contrat : this.getAll()) {
            if (contrat.getVehicule().equals(vehiculeId) && contrat.getEtat() == EtatContrat.IN_PROGRESS) {
                contrat.setEtat(EtatContrat.CANCELED);
                contrat.setMotifAnnulation("Véhicule déclaré en panne");
                contrat.setDateModification(LocalDateTime.now());
                this.bddService.save(contrat);
            }
        }
    }

}