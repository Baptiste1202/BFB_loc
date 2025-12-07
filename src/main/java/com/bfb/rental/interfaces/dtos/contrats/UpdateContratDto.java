package com.bfb.rental.interfaces.dtos.contrats;

import com.bfb.rental.business.common.EtatContrat;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "IN_PROGRESS", description = "État du contrat (IN_PROGRESS, ACTIVE, LATE, ENDED, CANCELED)")
    private EtatContrat etat;

    @Schema(example = "2025-12-07", description = "Date de début")
    private LocalDate dateDebut;

    @Schema(example = "2025-12-09", description = "Date de fin")
    private LocalDate dateFin;

    @Positive(message = "Le montant doit être positif")
    @Schema(example = "150.00", description = "Montant total")
    private BigDecimal montantTotal;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100000")
    private BigDecimal penaliteRetard;

    private LocalDate dateRetourReel;

    private String motifAnnulation;

    public static void merge(final UpdateContratDto dto, final com.bfb.rental.business.contrats.model.Contrat existing) {
        if (dto == null) return;

        if (dto.getEtat() != null) {
            existing.setEtat(dto.getEtat());
        }
        if (dto.getDateDebut() != null) {
            existing.setDateDebut(dto.getDateDebut());
        }
        if (dto.getDateFin() != null) {
            existing.setDateFin(dto.getDateFin());
        }
        if (dto.getMontantTotal() != null) {
            existing.setMontantTotal(dto.getMontantTotal());
        }
        if (dto.getPenaliteRetard() != null) {
            existing.setPenaliteRetard(dto.getPenaliteRetard());
        }
        if (dto.getDateRetourReel() != null) {
            existing.setDateRetourReel(dto.getDateRetourReel());
        }
        if (dto.getMotifAnnulation() != null) {
            existing.setMotifAnnulation(dto.getMotifAnnulation());
        }

        existing.setDateModification(LocalDateTime.now());
    }
}