package com.bfb.rental.business.vehicles.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateCamionDto extends UpdateVehiculeDto {

    @Positive(message = "Le volume doit être positif")
    @DecimalMax(value = "50.00", message = "Le volume ne peut pas dépasser 50m³")
    @DecimalMin(value = "5.00", message = "Le volume doit être au minimum 5m³")
    @Schema(example = "25.5", description = "Volume en m³")
    private Double volume;
}