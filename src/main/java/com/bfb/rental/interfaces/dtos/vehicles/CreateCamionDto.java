package com.bfb.rental.interfaces.dtos.vehicles;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateCamionDto extends CreateVehiculeDto {

    @NotNull(message = "Le volume est obligatoire")
    @Positive(message = "Le volume doit être positif")
    @DecimalMax(value = "50.00", message = "Le volume ne peut pas dépasser 50m³")
    @DecimalMin(value = "5.00", message = "Le volume doit être au minimum 5m³")
    private Double volume;

}
