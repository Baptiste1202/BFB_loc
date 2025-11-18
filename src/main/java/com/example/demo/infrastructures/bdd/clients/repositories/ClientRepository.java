package com.example.demo.infrastructures.bdd.clients.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}

