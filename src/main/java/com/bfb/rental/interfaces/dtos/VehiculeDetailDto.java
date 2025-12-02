package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JUSTIFICATION : DTO de détail pour un véhicule
 *
 * GET /api/vehicules/:id retourne TOUS les détails :
 * - Infos du véhicule (VehiculeDto)
 * - Historique de location
 * - Contrat actuel (s'il existe)
 *
 * Vs GET /api/vehicules → retourne juste VehiculeDto (light)
 *
 * PATTERN : Enriched DTO Pattern
 * BENEFIT : Performance (pas de N+1, pas de data inutile dans les listes)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VehiculeDetailDto extends VehiculeDto {

    /**
     * JUSTIFICATION : Historique complet des locations
     *
     * Important pour :
     * - Audit : qui a loué ce véhicule, quand
     * - Maintenance : suivre l'utilisation (kilométrage, dégâts, etc.)
     * - Décisions : Ce véhicule a beaucoup d'annulations ? Vérifier la qualité
     *
     * ANTI-PATTERN ÉVITÉ : Charger TOUS les contrats (Eager loading)
     * ✅ PATTERN : Lazy loading ou query explicite
     *
     * On ne charge que les infos minimales pour afficher une liste :
     * - ID contrat, dates, état, client
     * - Pas toute la complexité du contrat (facture, pénalité, etc.)
     */
    @JsonProperty("historique")
    private List<HistoriqueLocationDto> historique = new ArrayList<>();

    /**
     * JUSTIFICATION : Contrat ACTUEL (EN_COURS)
     *
     * S'il n'y a pas de contrat actuel (véhicule disponible), c'est null.
     *
     * Important pour :
     * - Tableau de bord : "Ce véhicule est loué par qui ?"
     * - Gestion : "Quand redevient-il disponible ?"
     */
    @JsonProperty("contratActuel")
    private ContratActuelDto contratActuel;

    /**
     * JUSTIFICATION : Stats du véhicule
     *
     * Données aggrégées utiles pour le dashboard
     */
    @JsonProperty("statistiques")
    private VehiculeStatistiquesDto statistiques;
}
