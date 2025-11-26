package com.example.demo.interfaces.vehicles.model.output;

import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.interfaces.common.model.output.AbstractOutput;
import com.example.demo.business.common.VehicleStateEnum;
import lombok.*;

import java.io.Serial;
import java.util.Date;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class VehicleOutput extends AbstractOutput {

    @Serial
    private static final long serialVersionUID = 3L;

    private final String identifier;
    private String brand;
    private String model;
    private String motorisation;
    private String color;
    private String immatriculation;
    private Date acquisition_date;
    private VehicleStateEnum etat;

    public static VehicleOutput from(Vehicle vehicle) {
        return VehicleOutput.builder()
                .identifier(vehicle.getIdentifier().toString())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .motorisation(vehicle.getMotorisation())
                .color(vehicle.getColor())
                .immatriculation(vehicle.getImmatriculation())
                .acquisition_date(vehicle.getAcquisition_date())
                .etat(vehicle.getEtat())
                .build();
    }
}
