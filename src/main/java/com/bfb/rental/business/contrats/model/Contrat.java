package com.bfb.rental.business.contrats.model;


import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {

    @NotBlank(message = "Le client est obligatoire")
    private String clientId;

    @NotBlank(message = "Le véhicule est obligatoire")
    private String vehiculeId;

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;
}