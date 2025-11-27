package com.bfb.rental.infrastructures.bdd.vehicules.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bfb.rental.infrastructures.bdd.vehicules.repositories.entities.VehiculeEntity;

public interface VehiculeRepository extends MongoRepository<VehiculeEntity, String> {
}