package com.bfb.rental.business.vehicles.dtos;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateVoitureDto.class, name = "voiture"),
        @JsonSubTypes.Type(value = CreateCamionDto.class, name = "camion")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CreateVehiculeDto",
        title = "Création de Véhicule",
        description = """
        Crée un nouveau véhicule. Choisir le type:
        - **voiture**: ajouter 'nombrePlaces' (2-9, défaut: 5)
        - **camion**: ajouter 'volume' en m³ (5-50, défaut: 20.0)
        """
)
public abstract class CreateVehiculeDto {

    @NotBlank(message = "Le type est obligatoire (voiture ou camion)")
    @Schema(example = "voiture", description = "Type de véhicule: voiture ou camion")
    private String type;

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