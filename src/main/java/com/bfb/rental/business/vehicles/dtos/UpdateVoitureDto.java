package com.bfb.rental.business.vehicles.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateVoitureDto extends UpdateVehiculeDto {

    @Min(value = 2, message = "Une voiture doit avoir au minimum 2 places")
    @Max(value = 9, message = "Une voiture ne peut pas avoir plus de 9 places")
    @Schema(example = "5", description = "Nombre de places")
    private Integer nombrePlaces;
}