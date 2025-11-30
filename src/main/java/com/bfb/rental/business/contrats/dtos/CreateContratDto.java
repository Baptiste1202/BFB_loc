package com.bfb.rental.business.contrats.dtos;

import com.bfb.rental.business.clients.model.Client;
import com.bfb.rental.business.vehicles.model.TransportVehicle;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContratDto {

    @NotNull(message = "Le client est obligatoire")
    private Client client;

    @NotNull(message = "Le véhicule est obligatoire")
    private TransportVehicle vehicule;

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;
}