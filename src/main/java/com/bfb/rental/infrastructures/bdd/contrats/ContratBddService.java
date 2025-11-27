package com.bfb.rental.infrastructures.bdd.contrats;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.infrastructures.bdd.contrats.repositories.ContratRepository;
import com.bfb.rental.infrastructures.bdd.contrats.repositories.entities.ContratEntity;
import com.bfb.rental.infrastructures.bdd.contrats.repositories.mappers.ContratBddMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContratBddService {

    private ContratRepository repository;
    private ContratBddMapper mapper;

    public boolean exist(final UUID identifier) {
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .map(this.repository::existsById)
                .orElse(false);
    }

    public Collection<Contrat> getAll() {
        return Objects.requireNonNullElse(this.repository.findAll(), Collections.<ContratEntity>emptyList())
                .stream()
                .map(this.mapper::from)
                .collect(Collectors.toSet());
    }

    public Optional<Contrat> get(final UUID identifier) {
        return Optional.ofNullable(identifier)
                .map(UUID::toString)
                .flatMap(this.repository::findById)
                .map(this.mapper::from);
    }

    public Contrat save(final Contrat contrat) {
        Objects.requireNonNull(contrat, "Impossible de sauvegarder un contrat nul");
        return this.mapper.from(
                this.repository.save(this.mapper.to(contrat))
        );
    }

    public void delete(final UUID identifier) {
        Optional.ofNullable(identifier)
                .map(UUID::toString)
                .ifPresent(this.repository::deleteById);
    }
}