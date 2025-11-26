package com.example.demo.interfaces.vehicles.model.input;

import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.interfaces.common.model.input.AbstractInput;
import com.example.demo.business.common.VehicleStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class VehicleInput extends AbstractInput {

    @Serial
    private static final long serialVersionUID = 1L;

    private String brand;
    private String model;
    private String motorisation;
    private String color;
    private String immatriculation;
    private Date acquisition_date;
    private VehicleStateEnum etat;

    public static Vehicle convert(VehicleInput input) {
        return Vehicle.builder()
                .identifier(UUID.randomUUID())
                .brand(input.getBrand())
                .model(input.getModel())
                .motorisation(input.getMotorisation())
                .color(input.getColor())
                .immatriculation(input.getImmatriculation())
                .acquisition_date(input.getAcquisition_date())
                .etat(input.getEtat() != null ? input.getEtat() : VehicleStateEnum.AVAILABLE)
                .build();
    }
}
