package com.bfb.rental.business.vehicles;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.vehicles.model.Vehicle;
import com.bfb.rental.common.util.CollectionImtUtil;
import com.bfb.rental.infrastructures.bdd.clients.ClientsBddService;
import com.example.demo.interfaces.common.exception.NotFoundException;

public class VehicleService {

    private final ClientsBddService service;

    public VehicleService(ClientsBddService service) {
        this.service = service;
    }

    /**
     * Récupère tous les comptes associés à un client donné.
     *
     * @param clientIdentifier Identifiant du client
     * @return Collection des véhicules associés au client, ou une collection vide si le client n'existe pas ou n'a pas de véhicules
     */
    public Collection<Vehicle> getAllFilteredByClient(final UUID clientIdentifier) {
        return this.service.get(clientIdentifier).map(Client::getVehicles).orElse(Collections.emptySet());
    }

    /**
     * Récupère un vehicule spécifique associé à un client donné.
     *
     * @param clientIdentifier Identifiant du client
     * @param identifier Identifiant du véhicule
     * @return Un Optional contenant le véhicule s'il existe, ou Optional.empty() sinon
     */
    public Optional<Vehicle> getOneFilteredByClient(final UUID clientIdentifier, final UUID identifier) {
        return this.service.get(clientIdentifier)
                .map(Client::getVehicles)
                .stream()
                .flatMap(Collection::stream)
                .filter(vehicle -> Objects.equals(vehicle.getIdentifier(), identifier))
                .findFirst();
    }

    /**
     * Associe un nouveau véhicule à un client donné.
     *
     * @param clientIdentifier Identifiant du client
     * @param newVehicle Nouveau véhicule à créer
     * @return Le vehicule créé
     * @throws NotFoundException si le client n'existe pas
     */
    public Vehicle create(final UUID clientIdentifier, final Vehicle newVehicle) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(client.toBuilder().vehicles(CollectionImtUtil.append(client.getVehicles(), newVehicle)).build());
        return newVehicle;
    }

    /**
     * Met à jour un compte existant d'un client.
     *
     * @param clientIdentifier Identifiant du client
     * @param updatedCompte Compte mis à jour
     * @throws NotFoundException si le client n'existe pas
     */
    public void update(final UUID clientIdentifier, final Vehicle updateVehicle) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(
                client.toBuilder().vehicles(CollectionImtUtil.append(
                        Objects.requireNonNullElse(client.getVehicles(), Collections.<Vehicle>emptySet())
                                .stream()
                                .filter(compte -> !Objects.equals(compte.getIdentifier(), updateVehicle.getIdentifier()))
                                .collect(Collectors.toSet()),
                        updateVehicle
                )).build()
        );
    }

    public void delete(final UUID clientIdentifier, final UUID identifier) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(client.toBuilder().vehicles(
                Objects.requireNonNullElse(client.getVehicles(), Collections.<Vehicle>emptySet())
                        .stream()
                        .filter(compte -> !Objects.equals(compte.getIdentifier(), identifier))
                        .collect(Collectors.toSet())
        ).build());
    }

}
