package com.bfb.rental.infrastructures.bdd.clients.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bfb.rental.infrastructures.bdd.clients.repositories.entities.ClientEntity;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}

