package com.example.demo.interfaces.vehicles.model.input;

import com.example.demo.business.vehicles.model.Vehicle;
import com.example.demo.business.common.VehicleStateEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

@Data
@NoArgsConstructor
public class VehicleUpdateInput {

    @Serial
    private static final long serialVersionUID = 2L;

    private String brand;
    private String model;
    private String motorisation;
    private String color;
    private String immatriculation;
    private Date acquisition_date;
    private VehicleStateEnum etat;

    public static Vehicle from(VehicleUpdateInput update, Vehicle alreadySaved) {
        return Vehicle.builder()
                .identifier(alreadySaved.getIdentifier())
                .brand(update.getBrand() != null ? update.getBrand() : alreadySaved.getBrand())
                .model(update.getModel() != null ? update.getModel() : alreadySaved.getModel())
                .motorisation(update.getMotorisation() != null ? update.getMotorisation() : alreadySaved.getMotorisation())
                .color(update.getColor() != null ? update.getColor() : alreadySaved.getColor())
                .immatriculation(update.getImmatriculation() != null ? update.getImmatriculation() : alreadySaved.getImmatriculation())
                .acquisition_date(update.getAcquisition_date() != null ? update.getAcquisition_date() : alreadySaved.getAcquisition_date())
                .etat(update.getEtat() != null ? update.getEtat() : alreadySaved.getEtat())
                .build();
    }
}