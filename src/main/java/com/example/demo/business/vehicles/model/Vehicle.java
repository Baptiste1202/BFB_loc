package com.example.demo.business.vehicles.model;

import java.util.Date;

import com.example.demo.business.common.VehicleStateEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Vehicle {
    
    /**
     * Marque du véhicule
     */
    private String brand;

    /**
     * Modèle du véhicule
     */
    private String model;

    /**
     * Motorisation du véhicule
     */
    private String motorisation;

    /**
     * Couleur du véhicule
     */
    private String color;

    /**
     * Numéro d'immatriculation du véhicule
     */
    private String immatriculation;

    /**
     * Date d'acquisition du véhicule
     */
    private Date acquisition_date;

    /**
     * Etat du véhicule
     */
    private VehicleStateEnum etat;
}
