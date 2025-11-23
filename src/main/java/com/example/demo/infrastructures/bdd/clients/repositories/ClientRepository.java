package com.example.demo.infrastructures.bdd.clients.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.infrastructures.bdd.clients.repositories.entities.ClientEntity;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}

