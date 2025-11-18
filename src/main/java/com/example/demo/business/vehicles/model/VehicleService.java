package com.example.demo.business.vehicles.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.example.demo.business.clients.model.Client;
import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.infrastructures.bdd.clients.ClientBddService;

public class VehicleService {

    private final ClientBddService service;

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
        return newCompte;
    }

    /**
     * Met à jour un compte existant d'un client.
     *
     * @param clientIdentifier Identifiant du client
     * @param updatedCompte Compte mis à jour
     * @throws NotFoundException si le client n'existe pas
     */
    public void update(final UUID clientIdentifier, final Vehicle updatedCompte) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(
                client.toBuilder().comptes(CollectionImtUtils.append(
                        Objects.requireNonNullElse(client.getComptes(), Collections.<Compte>emptySet())
                                .stream()
                                .filter(compte -> !Objects.equals(compte.getIdentifier(), updatedCompte.getIdentifier()))
                                .collect(Collectors.toSet()),
                        updatedCompte
                )).build()
        );
        this.mouvementPublisher.accept(clientIdentifier, updatedCompte);
    }

}
