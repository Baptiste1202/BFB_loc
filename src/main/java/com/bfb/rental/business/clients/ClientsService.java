package com.bfb.rental.business.clients;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.infrastructures.bdd.clients.ClientsBddService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public class ClientsService {

    /**
     * Service d'accès à la base de données pour les clients
     */
    protected ClientsBddService service;
    
    /**
     * Récupère tous les clients.
     *
     * @return une collection de tous les clients, ou une collection vide si aucun client n'existe
     */
    public Collection<Client> getAll() {
       return Objects.requireNonNullElse(this.service.getAll(), Collections.emptySet());
    }

    /**
     * Récupère un client spécifique par son identifiant.
     *
     * @param identifier l'identifiant unique du client
     * @return un Optional contenant le client s'il existe, Optional.empty() sinon
     */
    public Optional<Client> getOne(final UUID identifier) {
        return this.service.get(identifier);
    }

    /**
     * Crée un nouveau client.
     *
     * @param newClient le nouveau client à créer
     * @return le client créé avec son identifiant généré
     */
    public Client create(final Client newClient) {
        return this.service.save(newClient);
    }

    /**
     * Met à jour un client existant.
     *
     * @param updatedClient le client avec les informations mises à jour
     */
    public void update(final Client updatedClient) {
        this.service.save(updatedClient);
    }

    /**
     * Supprime un client par son identifiant.
     *
     * @param identifier l'identifiant unique du client à supprimer
     */
    public void delete(final UUID identifier) {
        this.service.delete(identifier);
    }

}