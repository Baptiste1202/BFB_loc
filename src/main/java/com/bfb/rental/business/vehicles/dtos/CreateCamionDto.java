package com.bfb.rental.business.vehicles.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCamionDto extends CreateVehiculeDto {

    @NotNull(message = "Le volume est obligatoire")
    @Positive(message = "Le volume doit être positif")
    @DecimalMax(value = "50.00", message = "Le volume ne peut pas dépasser 50m³")
    @DecimalMin(value = "5.00", message = "Le volume doit être au minimum 5m³")
    private Double volume;

}
