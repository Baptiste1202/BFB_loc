package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
/**
 * JUSTIFICATION : DTO de référence pour Client
 *
 * Quand on inclut un Client dans une autre DTO, ici 'contrat"
 * on ne veut pas TOUTES ses infos, juste celles pour l'afficher.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientReferenceDto {

    private String id;
    private String nom;
    private String prenom;
    private String numPermis;

    @JsonProperty("nomComplet")
    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
