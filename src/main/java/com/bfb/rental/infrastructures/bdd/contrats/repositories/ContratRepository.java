package com.bfb.rental.infrastructures.bdd.contrats.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bfb.rental.infrastructures.bdd.contrats.repositories.entities.ContratEntity;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ContratRepository extends MongoRepository<ContratEntity, String> {
    @Query("{ 'vehiculeId': ?0, 'etat': { $in: ['IN_PROGRESS', 'ACTIVE'] }}")
    List<ContratEntity> findConflictingContracts(String vehiculeId, LocalDate dateDebut, LocalDate dateFin);

    @Query("{ 'vehiculeId': ?0, 'etat': 'IN_PROGRESS' }")
    List<ContratEntity> findInProgressByVehicule(String vehiculeId);
}