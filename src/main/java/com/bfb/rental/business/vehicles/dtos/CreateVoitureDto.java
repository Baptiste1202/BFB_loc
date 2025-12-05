package com.bfb.rental.business.vehicles.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateVoitureDto extends CreateVehiculeDto{

    @NotNull(message = "Le nombre de places est obligatoire")
    @Min(value = 2, message = "Une voiture doit avoir au minimum 2 places")
    @Max(value = 9, message = "Une voiture ne peut pas avoir plus de 9 places")
    private Integer nombrePlaces;
}
