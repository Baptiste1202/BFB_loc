package com.bfb.rental.business.clients.model;

import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

/**
 * JUSTIFICATION : DTO séparé pour la création
 *
 * POURQUOI NE PAS RÉUTILISER ClientDto ?
 * ✅ Logique métier différente : L'ID est généré (pas en input)
 * ✅ Validation différente : dateCreation ne doit pas être en input
 * ✅ Immuabilité : Un DTO de réponse ne doit pas être modifié
 * ✅ Clarté : createClientDto vs clientDto = deux intentions claires
 *
 * PATTERN : Request/Response Separation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit être entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 100, message = "Le prénom doit être entre 2 et 100 caractères")
    private String prenom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @NotBlank(message = "Le numéro de permis est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{8,15}$", message = "Format de permis invalide")
    private String numPermis;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(min = 5, max = 255, message = "L'adresse doit être entre 5 et 255 caractères")
    private String adresse;
}
