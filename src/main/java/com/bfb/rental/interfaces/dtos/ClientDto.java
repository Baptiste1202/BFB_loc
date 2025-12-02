package com.bfb.rental.interfaces.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;

import java.time.LocalDate;

/**
 Ce ClientDto représente les données d’un client telles qu’elles seront renvoyées vers l’API.
 * CLIENT EST UN CONCEPT API ≠ CLIENT EST UNE ENTITÉ DB
 *
 * DTO = Data Transfer Object (PATTERN)
 * Responsabilité : Définir la structure des données EXPOSÉES à l'API
 *
 * POURQUOI SÉPARER DTO ET ENTITÉ ?
 * ✅ Sécurité : empêcher d’exposer la structure interne de la base,
 * ✅ Évolution : Je peux changer l'entity sans impacter les clients API
 * ✅ Performance : contrôler les données visibles par le front,
 * ✅ Validation : Validator.validate(dto) avant de créer entity
 *
 * PATTERN : Data Transfer Object Pattern (DTO / Value Object)
 * DESIGN PATTERN : Façade des données
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numPermis;
    private String adresse;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private Integer nombreContratsActifs;

    @JsonProperty("nomComplet")
    public String getNomComplet() {
        return prenom + " " + nom;
    }
}