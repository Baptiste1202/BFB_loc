package com.bfb.rental.infrastructures.bdd.contrats.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bfb.rental.infrastructures.bdd.contrats.repositories.entities.ContratEntity;

public interface ContratRepository extends MongoRepository<ContratEntity, String> {
}