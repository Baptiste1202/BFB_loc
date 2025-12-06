package com.bfb.rental.business.clients.dtos;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDto {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 100)
    private String prenom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private String dateNaissance;

    @NotBlank(message = "Le numéro de permis est obligatoire")
    @Pattern(regexp = "^[A-Z0-9]{8,15}$", message = "Format de permis invalide")
    private String numPermis;

    @NotBlank(message = "L'adresse est obligatoire")
    @Size(min = 5, max = 255)
    private String adresse;
}