package com.bfb.rental.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehiculeDto {

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

    /**
     * JUSTIFICATION : état ne peut PAS être modifié directement
     *
     * Les changements d'état doivent passer par des endpoints spécifiques :
     * - PATCH /vehicules/:id/panne → EN_PANNE
     * - PATCH /vehicules/:id/reparer → DISPONIBLE
     *
     * Pourquoi ? Car ce sont des actions métier complexes qui ont des effets de bord :
     * - Déclarer en panne → annule les contrats EN_ATTENTE
     * - Réparer → génère une notification
     *
     * PATTERN : Command Pattern
     * Les changements d'état = commandes spécifiques, pas des updates simples
     */

    /**
     * Méthode utilitaire : vérifier si le DTO est vide
     */
    @JsonIgnore
    public boolean isEmpty() {
        return marque == null &&
                modele == null &&
                motorisation == null &&
                couleur == null &&
                dateAcquisition == null &&
                prixLocationJournalier == null;
    }

    /**
     * Méthode utilitaire : obtenir les champs modifiés
     */
    @JsonIgnore
    public List<String> getModifiedFields() {
        List<String> fields = new ArrayList<>();

        if (marque != null) fields.add("marque");
        if (modele != null) fields.add("modele");
        if (motorisation != null) fields.add("motorisation");
        if (couleur != null) fields.add("couleur");
        if (dateAcquisition != null) fields.add("dateAcquisition");
        if (prixLocationJournalier != null) fields.add("prixLocationJournalier");

        return fields;
    }
}