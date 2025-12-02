package com.bfb.rental.business.vehicles.dtos;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CreateVehiculeDto {

    @NotBlank(message = "La marque est obligatoire")
    @Size(min = 2, max = 50)
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(min = 2, max = 50)
    private String modele;

    @NotBlank(message = "La motorisation est obligatoire")
    @Size(min = 2, max = 50)
    private String motorisation;

    @NotBlank(message = "La couleur est obligatoire")
    @Size(min = 2, max = 50)
    private String couleur;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{4,10}$", message = "Format d'immatriculation invalide")
    private String immatriculation;

    @NotNull(message = "La date d'acquisition est obligatoire")
    @PastOrPresent(message = "La date d'acquisition ne peut pas être dans le futur")
    private LocalDate dateAcquisition;

    @NotNull(message = "Le prix de location est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @DecimalMax(value = "10000.00", message = "Le prix ne peut pas dépasser 10000€")
    @DecimalMin(value = "10.00", message = "Le prix doit être au minimum 10€")
    private BigDecimal prixLocationJournalier;
}