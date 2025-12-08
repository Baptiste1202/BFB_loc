package com.bfb.rental.interfaces.dtos.vehicles;

import com.bfb.rental.business.common.EtatVehicule;
import com.bfb.rental.business.contrats.model.Contrat;
import com.bfb.rental.business.vehicles.model.Camion;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import com.bfb.rental.business.vehicles.model.Voiture;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * JUSTIFICATION : DTO pour modification partielle d'un véhicule
 *
 * Même logique que UpdateClientDto : PATCH (modification partielle)
 * Tous les champs sont OPTIONNELS.
 *
 * Les champs qui NE PEUVENT PAS être modifiés :
 * - id (généré)
 * - dateCreation (immutable)
 * - dateModification (générée côté serveur)
 * - immatriculation (unique et identificateur légal)
 *
 * Les champs modifiables :
 * - marque, modele, motorisation, couleur (infos du véhicule)
 * - dateAcquisition (si correction nécessaire)
 * - prixLocationJournalier (tarification peut changer)
 * - etat (MAIS attention : logique métier complexe)
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UpdateVoitureDto.class, name = "voiture"),
        @JsonSubTypes.Type(value = UpdateCamionDto.class, name = "camion")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehiculeDto {

    @NotBlank(message = "Le type est obligatoire (voiture ou camion)")
    @Schema(
            example = "voiture",
            description = "Type de véhicule (optionnel, non modifiable)",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String type;

    @Size(min = 2, max = 50, message = "La marque doit être entre 2 et 50 caractères")
    private String marque;

    @Size(min = 2, max = 50, message = "Le modèle doit être entre 2 et 50 caractères")
    private String modele;

    @Size(min = 2, max = 50, message = "La motorisation doit être entre 2 et 50 caractères")
    private String motorisation;

    @Size(min = 2, max = 50, message = "La couleur doit être entre 2 et 50 caractères")
    private String couleur;

    /**
     * JUSTIFICATION : dateAcquisition peut être modifiée
     *
     * Cas d'usage : correction d'une erreur lors de la création
     * Validation : ne peut pas être dans le futur
     */
    @PastOrPresent(message = "La date d'acquisition ne peut pas être dans le futur")
    private LocalDate dateAcquisition;

    /**
     * JUSTIFICATION : prix peut être modifié (tarification change)
     */
    @Positive(message = "Le prix doit être positif")
    @DecimalMax(value = "10000")
    @DecimalMin(value = "10")
    private BigDecimal prixLocationJournalier;


    @Schema(
            example = "AVAILABLE",
            description = "État initial du véhicule (AVAILABLE, RENTED, BROKE). Défaut: AVAILABLE"
    )
    private EtatVehicule etat;

    private Integer nombrePlaces;
    private Double volume;

    public static TransportVehicle merge(UpdateVehiculeDto dto, TransportVehicle existing) {

        if (dto.getMarque() != null) {
            existing.setMarque(dto.getMarque());
        }
        if (dto.getModele() != null) {
            existing.setModele(dto.getModele());
        }
        if (dto.getMotorisation() != null) {
            existing.setMotorisation(dto.getMotorisation());
        }
        if (dto.getCouleur() != null) {
            existing.setCouleur(dto.getCouleur());
        }
        if (dto.getPrixLocationJournalier() != null) {
            existing.setPrixLocationJournalier(dto.getPrixLocationJournalier());
        }
        if (dto.getEtat() != null) {
            existing.setEtat(dto.getEtat());
        }

        // Mise à jour des propriétés spécifiques selon le type
        if (existing instanceof Voiture voiture && dto.getNombrePlaces() != null) {
            voiture.setNombrePlaces(dto.getNombrePlaces());
        } else if (existing instanceof Camion camion && dto.getVolume() != null) {
            camion.setVolume(dto.getVolume());
        }

        // Mise à jour de la date de modification
        existing.setDateModification(LocalDateTime.now());

        return existing;
    }

}