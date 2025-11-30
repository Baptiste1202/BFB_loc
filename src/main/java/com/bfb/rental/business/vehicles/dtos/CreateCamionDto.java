package com.bfb.rental.business.vehicles.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCamionDto {

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
    @DecimalMax(value = "20000.00", message = "Le prix ne peut pas dépasser 20000€")
    @DecimalMin(value = "50.00", message = "Le prix doit être au minimum 50€")
    private BigDecimal prixLocationJournalier;

    @NotNull(message = "Le volume est obligatoire")
    @Positive(message = "Le volume doit être positif")
    @DecimalMax(value = "50.00", message = "Le volume ne peut pas dépasser 50m³")
    @DecimalMin(value = "5.00", message = "Le volume doit être au minimum 5m³")
    private Double volume;

    // Getters
    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getMotorisation() {
        return motorisation;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public LocalDate getDateAcquisition() {
        return dateAcquisition;
    }

    public BigDecimal getPrixLocationJournalier() {
        return prixLocationJournalier;
    }

    public Double getVolume() {
        return volume;
    }

    // Setters
    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMotorisation(String motorisation) {
        this.motorisation = motorisation;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public void setDateAcquisition(LocalDate dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public void setPrixLocationJournalier(BigDecimal prixLocationJournalier) {
        this.prixLocationJournalier = prixLocationJournalier;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
