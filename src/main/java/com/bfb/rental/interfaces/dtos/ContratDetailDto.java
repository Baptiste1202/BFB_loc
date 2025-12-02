package com.bfb.rental.interfaces.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.*;
import java.math.BigDecimal;
/**
 * JUSTIFICATION : DTO de détail pour GET /:id
 *
 * Inclut les données complètes du client et véhicule liés.
 * Utile pour afficher le détail d'un contrat avec toutes les infos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratDetailDto extends ContratDto {

    /**
     * Infos du client (seulement ce qu'on a besoin d'afficher)
     */
    @JsonProperty("client")
    private ClientReferenceDto client;

    /**
     * Infos du véhicule
     */
    @JsonProperty("vehicule")
    private VehiculeReferenceDto vehicule;

    /**
     * Infos spécifiques au contrat
     */
    private LocalDate dateRetourReel;
    private BigDecimal penaliteRetard;
}