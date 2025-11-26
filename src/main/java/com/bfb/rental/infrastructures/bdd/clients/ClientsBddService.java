package com.bfb.rental.infrastructures.bdd.clients;


import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.infrastructures.bdd.clients.repositories.ClientRepository;
import com.bfb.rental.infrastructures.bdd.clients.repositories.entities.ClientEntity;
import com.bfb.rental.infrastructures.bdd.clients.repositories.mappers.ClientBddMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientsBddService {
    private ClientRepository repository;
    private ClientBddMapper mapper;

    public boolean exist(final UUID identifier){
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .map(this.repository::existsById)
                .orElse(false);
    }

    public Collection<Client> getAll() {
        return Objects.requireNonNullElse(this.repository.findAll(), Collections.<ClientEntity>emptyList())
                .stream()
                .map(this.mapper::from)
                .collect(Collectors.toSet());
    }

    public Optional<Client> get(final UUID identifier){
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .flatMap(this.repository::findById)
                .map(this.mapper::from);
    }

    public Client save(final Client client){
        Objects.requireNonNull(client, "Impossible de sauvegarder un client nul");
        return this.mapper.from(
                this.repository.save(
                        this.mapper.to(client)
                )
        );
    }

    public void delete(final UUID identifier){
        Optional.ofNullable(identifier)
                .map(UUID::toString)
                .ifPresent(this.repository::deleteById);
    }
}
