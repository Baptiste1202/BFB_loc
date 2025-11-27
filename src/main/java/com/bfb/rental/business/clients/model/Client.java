package com.bfb.rental.business.clients.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private UUID id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numPermis;
    private String adresse;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}