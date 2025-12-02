package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.*;
/**
 * JUSTIFICATION : DTO Statistiques Client
 *
 * Pourquoi un DTO séparé pour les statistiques ?
 *
 * ✅ Separation of concerns : Les stats ≠ les données du client
 * ✅ Performance : On peut calculer les stats à la demande (GET /clients/:id)
 *                 et ne pas les calculer pour les listes (GET /clients)
 * ✅ Flexibilité : Facile d'ajouter de nouvelles stats
 * ✅ Réutilisabilité : Les stats peuvent être utilisées ailleurs
 *
 * PATTERN : Value Object Pattern (immuable, pas d'ID)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientStatistiquesDto {

    private Integer totalContrats;

    private Integer contratsActifs;

    private Integer contratsTermines;

    private Integer contratsEnRetard;

    private Integer contratsAnnules;

    private BigDecimal montantTotal;
}