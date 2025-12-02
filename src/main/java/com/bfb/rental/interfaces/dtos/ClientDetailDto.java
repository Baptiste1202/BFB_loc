package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JUSTIFICATION : DTO avec relations
 *
 * POURQUOI DEUX DTOs (ClientDto vs ClientDetailDto) ?
 * ✅ Performance : ListClients ne charge pas les contrats (N+1 problem)
 * ✅ Flexibilité : GET /clients → ClientDto (light)
 *                  GET /clients/:id → ClientDetailDto (complet)
 *
 * PATTERN : Enriched DTO Pattern
 * ANTI-PATTERN ÉVITÉ : Eager loading de tout
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDetailDto extends ClientDto {

    /**
     * Contrats du client
     * On pourrait charger seulement les contrats actifs
     * ou les 10 derniers, selon le métier
     */
    private List<ContratReferenceDto> contrats = new ArrayList<>();

    /**
     * Données aggrégées pour le dashboard
     */
    @JsonProperty("statistiques")
    private ClientStatistiquesDto statistiques;
}