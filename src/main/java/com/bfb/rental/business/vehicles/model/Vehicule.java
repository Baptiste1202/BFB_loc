package com.bfb.rental.interfaces.dtos;

import java.math.BigDecimal;

import com.bfb.rental.common.constants.AppConstants;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
/**
 * JUSTIFICATION : DTO de création avec validation
 *
 * Séparé de VehiculeDto car :
 * - L'ID ne doit pas être en input
 * - dateCreation est générée côté serveur
 * - etat est toujours DISPONIBLE au départ
 * - validation métier différente
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @NotBlank(message = "La motorisation est obligatoire")
    private String motorisation;

    @NotBlank(message = "La couleur est obligatoire")
    private String couleur;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{4,10}$", message = "Format d'immatriculation invalide")
    private String immatriculation;

    @NotNull(message = "La date d'acquisition est obligatoire")
    @PastOrPresent(message = "La date d'acquisition ne peut pas être dans le futur")
    private LocalDate dateAcquisition;

    @NotNull(message = "Le prix de location est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @DecimalMax(value = AppConstants.PRIX_LOCATION_MAX)
    @DecimalMin(value = AppConstants.PRIX_LOCATION_MIN)
    private BigDecimal prixLocationJournalier;
}