package com.bfb.rental.interfaces.dtos.vehicles;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
