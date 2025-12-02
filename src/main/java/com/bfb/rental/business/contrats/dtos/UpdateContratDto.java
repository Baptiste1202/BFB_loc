package com.bfb.rental.business.contrats.dtos;

import com.bfb.rental.business.contrats.model.Contrat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateContratDto {

    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    private LocalDate dateDebut;

    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;

    @Positive(message = "Le montant doit être positif")
    private BigDecimal montantTotal;

    @DecimalMin(value = "0.00")
    @DecimalMax(value = "100000.00")
    private BigDecimal penaliteRetard;

    @Size(max = 500, message = "Le motif d'annulation ne doit pas dépasser 500 caractères")
    private String motifAnnulation;

    public static Contrat merge(final UpdateContratDto dto, final Contrat existing) {
        if (dto.dateDebut != null) existing.setDateDebut(dto.dateDebut);
        if (dto.dateFin != null) existing.setDateFin(dto.dateFin);
        if (dto.montantTotal != null) existing.setMontantTotal(dto.montantTotal);
        if (dto.penaliteRetard != null) existing.setPenaliteRetard(dto.penaliteRetard);
        if (dto.motifAnnulation != null) existing.setMotifAnnulation(dto.motifAnnulation);
        existing.setDateModification(LocalDateTime.now());
        return existing;
    }
}